package edu.bdic.forbiddenisland.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 单例游戏模型：管理地图、玩家、牌堆、回合，并通知所有 ModelChangeListener
 */
public class GameModel {
    /** 地图 Tile 状态 */
    public enum TileState { DRY, FLOODED, SUNK }
    /** 游戏状态 */
    public enum GameState { ONGOING, WON, LOST }

    /** 地块对象 */
    public static class Tile {
        private final int x, y;
        private TileState state = TileState.DRY;
        public Tile(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; }
        public int getY() { return y; }
        public TileState getState() { return state; }
        public void flood() {
            if (state == TileState.DRY) state = TileState.FLOODED;
            else if (state == TileState.FLOODED) state = TileState.SUNK;
        }
        public void shoreUp() {
            if (state == TileState.FLOODED) state = TileState.DRY;
        }
        public void setState(TileState s) { state = s; }
    }

    private static final GameModel INSTANCE = new GameModel();
    public static GameModel getInstance() { return INSTANCE; }

    // —— 地图 & 岛屿布局 ——
    private final Tile[][] board = new Tile[6][6];
    private List<Integer> islandLayout = List.of();

    // —— 玩家管理 ——
    private final Map<Integer, Player> players = new HashMap<>();

    // —— 回合 & 行动点管理 ——
    private int currentPlayer = -1;
    private final Map<Integer,Integer> actionPoints = new HashMap<>();

    // —— 牌堆管理 ——
    private final List<Card> floodDeckList = new ArrayList<>();
    private Deck<Card> floodDeck;
    private final Map<Integer, List<TreasureCardType>> playerTreasureHands = new HashMap<>();
    private Deck<TreasureCardType> treasureDeck;
    private final List<TreasureCardType> treasureDiscard = new ArrayList<>();

    // —— 游戏等级 ——
    private int waterLevel = 2;

    // —— 直升机救援卡使用标志 ——
    private boolean helicopterLiftUsed = false;

    // —— 当前胜负状态 ——
    private GameState gameState = GameState.ONGOING;

    // —— 幂等保护 ——
    private long lastInitializedSeed = Long.MIN_VALUE;

    // —— 侦听器 ——
    private final List<ModelChangeListener> listeners = new ArrayList<>();

    // —— 宝藏岛映射 ——
    private static final Map<TreasureCardType, List<Integer>> TREASURE_ISLANDS =
            new EnumMap<>(TreasureCardType.class);
    static {
        TREASURE_ISLANDS.put(TreasureCardType.FIRE,  Arrays.asList(2, 3));
        TREASURE_ISLANDS.put(TreasureCardType.EARTH, Arrays.asList(18, 19));
        TREASURE_ISLANDS.put(TreasureCardType.WIND,  Arrays.asList(11, 23));
        TREASURE_ISLANDS.put(TreasureCardType.OCEAN, Arrays.asList(6, 20));
    }

    private GameModel() {
        // 初始化 6×6 地图
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++)
                board[x][y] = new Tile(x, y);
        // 构造洪水牌列表
        for (int t = 0; t < 24; t++) floodDeckList.add(new Card(t));
    }

    // —— 直升机救援卡使用 ——
    public void useHelicopterLift() {
        this.helicopterLiftUsed = true;
        fireChange();
    }

    // —— 设置岛屿布局 ——
    public void setIslandLayout(List<Integer> layout) {
        if (layout == null || layout.size() != 24)
            throw new IllegalArgumentException("Invalid layout size: must be 24");
        this.islandLayout = List.copyOf(layout);
        fireChange();
    }
    public List<Integer> getIslandLayout() { return islandLayout; }

    // —— 玩家管理 ——
    public void addPlayer(int playerIndex, Profession prof) {
        if (!players.containsKey(playerIndex)) {
            int spawnTile = SPAWN_MAP.get(prof);
            players.put(playerIndex, new Player(playerIndex, prof, spawnTile));
            fireChange();
        }
    }
    private static final Map<Profession,Integer> SPAWN_MAP = new EnumMap<>(Profession.class);
    static {
        SPAWN_MAP.put(Profession.DIVER,      12);
        SPAWN_MAP.put(Profession.ENGINEER,    1);
        SPAWN_MAP.put(Profession.EXPLORER,    5);
        SPAWN_MAP.put(Profession.NAVIGATOR,  10);
        SPAWN_MAP.put(Profession.PILOT,       9);
        SPAWN_MAP.put(Profession.MESSENGER,  17);
    }
    public Collection<Player> getPlayers() { return players.values(); }
    public Player getPlayer(int idx)   { return players.get(idx);   }

    // —— 初始化游戏 ——
    public void initializeGame(long seed) {
        if (seed == lastInitializedSeed) return;
        lastInitializedSeed = seed;

        this.gameState = GameState.ONGOING;
        this.helicopterLiftUsed = false;

        // 重置地块状态
        for (int x = 0; x < 6; x++)
            for (int y = 0; y < 6; y++)
                board[x][y].setState(TileState.DRY);

        // 洗洪水牌
        List<Card> fd = new ArrayList<>(floodDeckList);
        Collections.shuffle(fd, new Random(seed ^ 0xCAFEBABE));
        floodDeck = new Deck<>(fd);

        // 准备宝藏牌
        List<TreasureCardType> treasures = new ArrayList<>();
        for (int i = 0; i < 5; i++) treasures.add(TreasureCardType.FIRE);
        for (int i = 0; i < 5; i++) treasures.add(TreasureCardType.WIND);
        for (int i = 0; i < 5; i++) treasures.add(TreasureCardType.EARTH);
        for (int i = 0; i < 5; i++) treasures.add(TreasureCardType.OCEAN);
        for (int i = 0; i < 3; i++) treasures.add(TreasureCardType.HELICOPTER);
        for (int i = 0; i < 2; i++) treasures.add(TreasureCardType.SANDBAG);
        for (int i = 0; i < 3; i++) treasures.add(TreasureCardType.WATERRISE);
        Collections.shuffle(treasures, new Random(seed));
        treasureDeck = new Deck<>(treasures);
        treasureDiscard.clear();

        // 发手牌
        playerTreasureHands.clear();
        List<Integer> sortedPids = new ArrayList<>(players.keySet());
        Collections.sort(sortedPids);
        for (int pid : sortedPids) {
            List<TreasureCardType> hand = new ArrayList<>();
            int wrCount = 0, drawn = 0;
            while (drawn < 2) {
                TreasureCardType t = treasureDeck.draw();
                if (t == TreasureCardType.WATERRISE) {
                    wrCount++;
                    if (wrCount == 1) {
                        increaseWaterLevel();
                        floodDeck.recycleDiscards();
                        fireChange();
                    }
                    treasureDiscard.add(t);
                } else {
                    hand.add(t);
                    drawn++;
                }
            }
            playerTreasureHands.put(pid, hand);
        }

        // 初始洪水
        for (int i = 0; i < 6; i++) {
            Card c = floodDeck.draw();
            if (c == null) continue;
            int idx = c.getTileIndex();
            board[idx/6][idx%6].flood();
            floodDeck.discard(c);
        }

        // 回合 & 行动点
        if (!sortedPids.isEmpty()) {
            currentPlayer = sortedPids.get(0);
            actionPoints.clear();
            for (int pid : sortedPids) actionPoints.put(pid, 3);
        }

        fireChange();
    }

    public TileState getTileState(int tileIndex) {
        return board[tileIndex/6][tileIndex%6].getState();
    }
    public int getCurrentPlayer()     { return currentPlayer; }
    public int getActionPoints(int pid){ return actionPoints.getOrDefault(pid, 0); }
    public GameState getGameState()   { return gameState; }

    /** 使用 1 点行动力 */
    public boolean useActionPoint(int playerIndex) {
        int before = actionPoints.getOrDefault(playerIndex, 0);
        if (before <= 0) return false;
        actionPoints.put(playerIndex, before - 1);
        return true;
    }

    /**
     * 结束回合：抽宝藏、抽洪水、判定胜负、切换玩家、重置AP
     */
    public void endTurn() {
        if (gameState != GameState.ONGOING) return;

        // 1) 抽宝藏
        drawTreasurePhase();
        // 2) 抽洪水
        drawFloodPhase();
        // 3) 胜负判定
        gameState = evaluateGameState();
        fireChange();
        if (gameState != GameState.ONGOING) {
            // 不再继续轮转
            return;
        }
        // 4) 切换玩家 & 重置行动点
        List<Integer> pids = new ArrayList<>(players.keySet());
        Collections.sort(pids);
        int idx = pids.indexOf(currentPlayer);
        if (idx != -1) {
            currentPlayer = pids.get((idx+1) % pids.size());
            actionPoints.put(currentPlayer, 3);
        }
        fireChange();
    }

    // —— 抽牌与洪水阶段 ——

    private void drawTreasurePhase() {
        int pid = currentPlayer;
        List<TreasureCardType> hand = playerTreasureHands.computeIfAbsent(pid, k -> new ArrayList<>());
        int drawn = 0;
        boolean firstWR = true;
        int maxAttempts = 10;
        while (drawn < 2 && maxAttempts-- > 0) {
            if (treasureDeck.isEmpty()) {
                if (treasureDiscard.isEmpty()) break;
                List<TreasureCardType> toShuffle = treasureDiscard.stream()
                        .filter(t -> t != TreasureCardType.WATERRISE)
                        .collect(Collectors.toList());
                treasureDiscard.clear();
                Collections.shuffle(toShuffle, new Random());
                treasureDeck = new Deck<>(toShuffle);
            }
            TreasureCardType t = treasureDeck.draw();
            if (t == null) break;
            if (t == TreasureCardType.WATERRISE) {
                treasureDiscard.add(t);
                if (firstWR) {
                    firstWR = false;
                    increaseWaterLevel();
                    floodDeck.recycleDiscards();
                    fireChange();
                }
            } else {
                hand.add(t);
                drawn++;
            }
        }
        if (drawn < 2) {
            System.out.println("[WARN] drawTreasurePhase: only drew " + drawn + " cards");
        }
    }

    private void drawFloodPhase() {
        int n = waterLevel;
        for (int i = 0; i < n; i++) {
            if (floodDeck.isEmpty()) floodDeck.recycleDiscards();
            Card c = floodDeck.draw();
            if (c == null) continue;
            int idx = c.getTileIndex();
            board[idx/6][idx%6].flood();
            floodDeck.discard(c);
        }
        fireChange();
    }

    // —— 移动与抽水 ——

    public void movePlayer(int playerIndex, int toTileIndex) {
        if (!useActionPoint(playerIndex)) {
            System.out.printf("[DEBUG][GameModel] movePlayer: player %d no AP%n", playerIndex);
            return;
        }
        Player p = players.get(playerIndex);
        if (p != null) {
            System.out.printf("[DEBUG][GameModel] player %d moves to tile %d%n", playerIndex, toTileIndex);
            p.moveTo(toTileIndex);
            fireChange();
        }
    }

    public void shoreUpTile(int tileIndex) {
        if (!useActionPoint(currentPlayer)) return;
        board[tileIndex/6][tileIndex%6].shoreUp();
        fireChange();
    }

    // 增加一个重载方法
    public void shoreUpTiles(int playerIdx, List<Integer> tileIndices) {
        if (!useActionPoint(playerIdx)) {
            System.out.printf("[DEBUG][Engineer] player=%d not enough AP for shore up%n", playerIdx);
            return;
        }
        for (int tileIndex : tileIndices) {
            board[tileIndex / 6][tileIndex % 6].shoreUp();
            System.out.printf("[DEBUG][Engineer] player=%d shore up tile %d%n", playerIdx, tileIndex);
        }
        fireChange();
    }


    // —— 手牌、弃牌、增水 ——

    public List<TreasureCardType> getTreasureHand(int pid) {
        return playerTreasureHands.getOrDefault(pid, Collections.emptyList());
    }

    public void discardTreasure(int pid, TreasureCardType card) {
        List<TreasureCardType> h = playerTreasureHands.get(pid);
        if (h != null && h.remove(card)) {
            System.out.printf("[LOG][GameModel.discardTreasure] 玩家 %d 弃掉 %s，剩余手牌=%s%n", pid, card, h);
            treasureDiscard.add(card);
            fireChange();
        }
    }

    public void increaseWaterLevel() {
        waterLevel++;
        fireChange();
    }

    public int getWaterLevel() { return waterLevel; }

    public boolean captureTreasure(int pid) {
        if (!useActionPoint(pid)) return false;
        Player p = players.get(pid);
        if (p == null) return false;
        int tile = p.getTileIndex();
        TreasureCardType tgt = TREASURE_ISLANDS.entrySet().stream()
                .filter(e -> e.getValue().contains(tile))
                .map(Map.Entry::getKey).findFirst().orElse(null);
        if (tgt == null) return false;
        List<TreasureCardType> h = playerTreasureHands.get(pid);
        if (h == null) return false;
        long cnt = h.stream().filter(c -> c == tgt).count();
        if (cnt < 4) return false;
        int rem = 0;
        Iterator<TreasureCardType> it = h.iterator();
        while (it.hasNext() && rem < 4) {
            if (it.next() == tgt) {
                it.remove();
                rem++;
            }
        }
        fireChange();
        return true;
    }

    public void giveCard(int fromPlayer, int toPlayer, TreasureCardType card) {
        if (!useActionPoint(fromPlayer)) {
            System.out.printf("[DEBUG][GameModel] giveCard: player %d no AP%n", fromPlayer);
            return;
        }
        List<TreasureCardType> fh = playerTreasureHands.get(fromPlayer);
        if (fh != null && fh.remove(card)) {
            System.out.printf("[DEBUG][GameModel] player=%d gives %s to player=%d%n", fromPlayer, card, toPlayer);
            playerTreasureHands.computeIfAbsent(toPlayer, k -> new ArrayList<>()).add(card);
            fireChange();
        }
    }

    /**
     * 评估当前游戏状态
     */
    public GameState evaluateGameState() {
        // 胜利：所有4种宝藏已收集 + 已用直升机卡 + 全体玩家都在 Fool's Landing 且未沉没
        if (TREASURE_ISLANDS.keySet().stream().allMatch(t -> treasureDiscard.contains(t))
                && helicopterLiftUsed
                && players.values().stream().allMatch(p ->
                !getTileState(p.getTileIndex()).equals(TileState.SUNK)
                        && isFoolsLanding(p.getTileIndex())))
        {
            return GameState.WON;
        }
        // 失败 1：某宝藏未收集，其两块岛都沉没
        for (var e : TREASURE_ISLANDS.entrySet()) {
            if (!treasureDiscard.contains(e.getKey())) {
                List<Integer> two = e.getValue();
                if (getTileState(two.get(0)) == TileState.SUNK
                        && getTileState(two.get(1)) == TileState.SUNK) {
                    return GameState.LOST;
                }
            }
        }
        // 失败 2：Fool's Landing 本身沉没
        int fool = findFoolsLandingIndex();
        if (fool >= 0 && getTileState(fool) == TileState.SUNK) {
            return GameState.LOST;
        }
        // 失败 3：玩家在沉没块，且四周无逃生路径
        for (Player p : players.values()) {
            int idx = p.getTileIndex();
            if (getTileState(idx) == TileState.SUNK) {
                boolean can = getAdjacentIndices(idx).stream()
                        .anyMatch(i -> getTileState(i) != TileState.SUNK);
                if (!can) return GameState.LOST;
            }
        }
        // 失败 4：水位 > 5
        if (waterLevel > 5) {
            return GameState.LOST;
        }
        return GameState.ONGOING;
    }

    private boolean isFoolsLanding(int tileIndex) {
        return tileIndex == 3;
    }
    private int findFoolsLandingIndex() {
        for (int i = 0; i < islandLayout.size(); i++) {
            if (isFoolsLanding(i)) return i;
        }
        return -1;
    }
    private List<Integer> getAdjacentIndices(int idx) {
        int x = idx / 6, y = idx % 6;
        List<Integer> out = new ArrayList<>();
        if (x > 0) out.add((x-1)*6 + y);
        if (x < 5) out.add((x+1)*6 + y);
        if (y > 0) out.add(x*6 + (y-1));
        if (y < 5) out.add(x*6 + (y+1));
        return out;
    }

    public void addListener(ModelChangeListener l)    { if (!listeners.contains(l)) listeners.add(l); }
    public void removeListener(ModelChangeListener l) { listeners.remove(l); }

    private void fireChange() {
        for (var l : listeners) l.onModelChanged();
    }

    /** 外部强制设置某个格子的状态（用于调试或网络同步） */
    public void updateTileStatus(int tileIndex, String status) {
        int x = tileIndex / 6, y = tileIndex % 6;
        board[x][y].setState(TileState.valueOf(status));
        fireChange();
    }
}
