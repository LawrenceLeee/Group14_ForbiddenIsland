package edu.bdic.forbiddenisland.view;

import edu.bdic.forbiddenisland.MainApp;
import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.controller.commands.*;
import edu.bdic.forbiddenisland.model.*;
import edu.bdic.forbiddenisland.util.Animations;
import edu.bdic.forbiddenisland.util.GameDialogUtil;
import edu.bdic.forbiddenisland.util.ImageFactory;
import edu.bdic.forbiddenisland.util.Notifier;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.stream.Collectors;

import static edu.bdic.forbiddenisland.model.GameModel.TileState.*;

public class GameController implements ModelChangeListener {
    @FXML private Label sessionLabel, playerLabel;
    @FXML private Button startGameButton, exitButton;
    @FXML private Button move, shoreUp, nextTurn, capture, give, useSkill;
    @FXML private BorderPane mainPane;
    @FXML private Label AP, waterLevel;
    private Stage stage;

    @FXML private ImageView
            island_0, island_1, island_2, island_3,
            island_4, island_5, island_6, island_7,
            island_8, island_9, island_10, island_11,
            island_12, island_13, island_14, island_15,
            island_16, island_17, island_18, island_19,
            island_20, island_21, island_22, island_23;
    @FXML private ImageView
            sand_0, sand_1, sand_2, sand_3,
            sand_4, sand_5, sand_6, sand_7,
            sand_8, sand_9, sand_10, sand_11,
            sand_12, sand_13, sand_14, sand_15,
            sand_16, sand_17, sand_18, sand_19,
            sand_20, sand_21, sand_22, sand_23;
    @FXML private ImageView
            card00, card01, card02, card03, card04,
            card10, card11, card12, card13, card14,
            card20, card21, card22, card23, card24,
            card30, card31, card32, card33, card34;
    @FXML private ImageView fire, wind, ocean, earth;
    @FXML private ImageView player0, player1, player2, player3;
    @FXML private ImageView playerOnePawn, playerTwoPawn, playerThreePawn, playerFourPawn;

    private enum ActionState  { NONE, MOVE, SHOREUP, FLY }
    private enum GiveState    { NONE, SELECT_CARD, SELECT_PLAYER }
    private enum DiscardState { NONE, MUST_DISCARD }
    private enum SkillState   { NONE, NAVI_SELECT_PLAYER, NAVI_MOVING_PLAYER }

    private SkillState skillState = SkillState.NONE;
    private int        naviTarget  = -1;
    private int        naviMoves   = 0;

    private ActionState  currentAction  = ActionState.NONE;
    private GiveState    giveState      = GiveState.NONE;
    private DiscardState discardState   = DiscardState.NONE;

    private int selectedCardIdx = -1;
    private List<Integer> validTargets = new ArrayList<>();

    private final List<ImageView> islands            = new ArrayList<>();
    private final List<ImageView> sands              = new ArrayList<>();
    private final List<ImageView> avatars            = new ArrayList<>();
    private final List<ImageView> pawns              = new ArrayList<>();
    private final List<List<ImageView>> cardViewsByPlayer = new ArrayList<>();
    private final Map<Integer,Integer> tileToView = new HashMap<>();
    private final Map<ImageView, Animation> highlightAnimations = new HashMap<>();
    private List<Integer> engineerShoreTargets = new ArrayList<>();

    private boolean pilotSkillUsed = false;
    private int     lastTurnPlayer = -1;

    private static final Map<TreasureCardType, List<Integer>> TREASURE_SITES = new HashMap<>();
    static {
        TREASURE_SITES.put(TreasureCardType.FIRE,  Arrays.asList(2, 3));
        TREASURE_SITES.put(TreasureCardType.EARTH, Arrays.asList(18, 19));
        TREASURE_SITES.put(TreasureCardType.WIND,  Arrays.asList(11, 23));
        TREASURE_SITES.put(TreasureCardType.OCEAN, Arrays.asList(6, 20));
    }

    @FXML
    public void initialize() {
        // 记录初始水位
        prevWaterLevel = GameModel.getInstance().getWaterLevel();

        // 监听游戏状态（胜利/失败），弹出对应对话框
        GameModel.getInstance().addListener(() -> {
            Platform.runLater(() -> {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                Runnable goToMenu = () -> {
                    GameModel.getInstance().initializeGame(Long.MIN_VALUE);
                    // new StartMenuView().init(stage);
                };
                if (GameModel.getInstance().getGameState() == GameModel.GameState.LOST) {
                    GameDialogUtil.showGameOverDialog(stage, goToMenu);
                } else if (GameModel.getInstance().getGameState() == GameModel.GameState.WON) {
                    GameDialogUtil.showGameWonDialog(stage, goToMenu);
                }
            });
        });

        // 场景元素初始化
        Collections.addAll(islands,
                island_0, island_1, island_2, island_3,
                island_4, island_5, island_6, island_7,
                island_8, island_9, island_10, island_11,
                island_12, island_13, island_14, island_15,
                island_16, island_17, island_18, island_19,
                island_20, island_21, island_22, island_23
        );
        Collections.addAll(sands,
                sand_0, sand_1, sand_2, sand_3,
                sand_4, sand_5, sand_6, sand_7,
                sand_8, sand_9, sand_10, sand_11,
                sand_12, sand_13, sand_14, sand_15,
                sand_16, sand_17, sand_18, sand_19,
                sand_20, sand_21, sand_22, sand_23
        );
        Collections.addAll(avatars, player0, player1, player2, player3);
        Collections.addAll(pawns,   playerOnePawn, playerTwoPawn, playerThreePawn, playerFourPawn);

        cardViewsByPlayer.add(Arrays.asList(card00, card01, card02, card03, card04));
        cardViewsByPlayer.add(Arrays.asList(card10, card11, card12, card13, card14));
        cardViewsByPlayer.add(Arrays.asList(card20, card21, card22, card23, card24));
        cardViewsByPlayer.add(Arrays.asList(card30, card31, card32, card33, card34));

        sessionLabel.setText("Session: " + SessionManager.getInstance().getSessionId());
        playerLabel.setText("Player:  " + SessionManager.getInstance().getPlayerId());
        startGameButton.setVisible(SessionManager.getInstance().isHost());

        // 注册自身为模型变化监听器，方便更新手牌、AP、waterLevel 等
        GameModel.getInstance().addListener(this);

        // 延迟获取 Stage 引用
        Platform.runLater(() -> stage = (Stage) mainPane.getScene().getWindow());

        // 首次刷新一次界面（防止在注册监听之前模型已发生变化）
        onModelChanged();
    }


    // —— 操作按钮 点击 ——

    private int getTileIndexFromImage(Object source) {
        if (source instanceof ImageView) {
            ImageView clickedIsland = (ImageView) source;
            System.out.println("Clicked on ImageView: " + clickedIsland);  // 调试输出

            // 在 islands 列表中找到这个 ImageView 并返回对应的索引
            for (int i = 0; i < islands.size(); i++) {
                if (islands.get(i) == clickedIsland) {
                    System.out.println("Found island at index: " + i);  // 调试输出
                    return i;  // 返回对应的岛屿索引
                }
            }
        }
        System.out.println("No matching island found for the clicked ImageView.");  // 调试输出
        return -1;  // 如果没有找到，返回 -1
    }

    @FXML
    void mouseClickedMove(MouseEvent e) {
        if (discardState != DiscardState.NONE) return;
        // 切换状态
        currentAction = (currentAction == ActionState.MOVE)
                ? ActionState.NONE
                : ActionState.MOVE;
        System.out.println("[DEBUG] mouseClickedMove → currentAction=" + currentAction);
        // 根据状态高亮对应岛屿
        updateHighlights();
    }


    @FXML void mouseClickedShoreUp(MouseEvent e) {
        if (discardState != DiscardState.NONE) return;
        currentAction = (currentAction == ActionState.SHOREUP) ? ActionState.NONE : ActionState.SHOREUP;
        engineerShoreTargets.clear();
        updateHighlights();
    }
    @FXML void mouseClickedUseSkill(MouseEvent e) {
        if (discardState != DiscardState.NONE) return;
        int me  = SessionManager.getInstance().getPlayerId();
        int cur = GameModel.getInstance().getCurrentPlayer();
        if (me != cur) {
            Notifier.notify(stage, "Not Your Turn!", "#f13242");
            return;
        }
        Player meP = GameModel.getInstance().getPlayer(me);
        switch (meP.getProfession()) {
            case PILOT:
                if (pilotSkillUsed) {
                    Notifier.notify(stage, "Flying skills have been used this turn", "#f13242");
                    return;
                }
                pilotSkillUsed = true;
                currentAction  = ActionState.FLY;  // 设置飞行状态
                highlightTiles(getFlyViewIndices());
                Notifier.notify(stage, "Pilot: Please choose any of the islands to fly over", "#2196F3");
                break;
            case NAVIGATOR:
                if (GameModel.getInstance().useActionPoint(me)) {
                    Notifier.notify(stage, "No Action Points!", "#f13242");
                    return;
                }
                skillState = SkillState.NAVI_SELECT_PLAYER;
                currentAction = ActionState.NONE;  // 清除普通移动状态，防止发生冲突
                for (int i = 0; i < avatars.size(); i++) {
                    if (i != me && i < GameModel.getInstance().getPlayers().size()) {
                        Animations.startFlash(avatars.get(i));
                    }
                }
                Notifier.notify(stage, "Navigator: Please select the player you want to move", "#2196F3");
                break;
            default:
                Notifier.notify(stage, "This character has no special skills available, has been integrated into ordinary operations", "#f13242");
        }
    }

    @FXML void mouseClickedNextTurn(MouseEvent e) {
        if (discardState != DiscardState.NONE) return;
        int me  = SessionManager.getInstance().getPlayerId();
        int cur = GameModel.getInstance().getCurrentPlayer();
        if (me != cur) {
            Notifier.notify(stage, "Not Your Turn!", "#f13242");
            return;
        }
        CommandManager.getInstance().executeAndSend(new NextTurnCommand());
    }
    @FXML void mouseClickedCapture(MouseEvent e) {
        if (discardState != DiscardState.NONE) return;
        int me = SessionManager.getInstance().getPlayerId();
        if (me != GameModel.getInstance().getCurrentPlayer()) {
            Notifier.notify(stage, "Not Your Turn!", "#f13242");
            return;
        }
        int tile = GameModel.getInstance().getPlayer(me).getTileIndex();
        TreasureCardType found = TREASURE_SITES.entrySet().stream()
                .filter(ent -> ent.getValue().contains(tile))
                .findFirst().map(Map.Entry::getKey).orElse(null);
        if (found == null) {
            Notifier.notify(stage, "This Island has no Treasure!", "#f13242");
            return;
        }
        if (GameModel.getInstance().useActionPoint(me)) {
            Notifier.notify(stage, "No Action Points!", "#f13242");
            return;
        }
        var hand = GameModel.getInstance().getTreasureHand(me);
        long cnt = hand.stream().filter(c -> c == found).count();
        if (cnt < 4) {
            Notifier.notify(stage, "Not enough " + found + " cards!", "#f13242");
            return;
        }
        int removed = 0;
        var it = hand.iterator();
        while (it.hasNext() && removed < 4) {
            if (it.next() == found) { it.remove(); removed++; }
        }
        ImageView icon;
        switch (found) {
            case EARTH: icon = earth; break;
            case WIND:  icon = wind;  break;
            case OCEAN: icon = ocean; break;
            default:    icon = fire;  break;
        }
        Animations.startFlash(icon);
        Notifier.notify(stage, "Captured the " + found + " treasure!", "#4CAF50");
        CommandManager.getInstance().executeAndSend(new CaptureCommand());
    }
    @FXML
    void mouseClickedGive(MouseEvent e) {
        // 如果正在强制弃牌阶段，屏蔽所有操作
        if (discardState != DiscardState.NONE) return;

        int me = SessionManager.getInstance().getPlayerId();
        // 只允许当前正在行动的玩家使用 Give
        if (me != GameModel.getInstance().getCurrentPlayer()) {
            Notifier.notify(stage, "Not Your Turn!", "#f13242");
            return;
        }

        // 根据职业筛选可给牌的玩家
        Player self = GameModel.getInstance().getPlayer(me);
        boolean isMessenger = self.getProfession() == Profession.MESSENGER;
        int myTile = self.getTileIndex();

        validTargets = GameModel.getInstance().getPlayers().stream()
                .map(Player::getPlayerIndex)
                .filter(idx -> idx != me)                         // 不能给自己
                .filter(idx -> isMessenger                         // 如果是 Messenger 可以给任何人
                        || GameModel.getInstance().getPlayer(idx).getTileIndex() == myTile)  // 否则必须和自己同岛
                .collect(Collectors.toList());

        // 如果没有可给的对象
        if (validTargets.isEmpty()) {
            Notifier.notify(stage,
                    isMessenger ? "No other players to give to!" : "No other players here!",
                    "#f13242");
            return;
        }

        // 进入【选卡片】阶段，闪烁自己手牌提示用户点击
        giveState = GiveState.SELECT_CARD;
        List<TreasureCardType> hand = GameModel.getInstance().getTreasureHand(me);
        List<ImageView> myCards = cardViewsByPlayer.get(me);
        for (int i = 0; i < hand.size() && i < myCards.size(); i++) {
            Animations.startFlash(myCards.get(i));
        }
    }


    // —— 岛屿点击 ——

    @FXML void mouseClickedIsland0(MouseEvent e){ handleIslandClick(0); }
    @FXML void mouseClickedIsland1(MouseEvent e){ handleIslandClick(1); }
    @FXML void mouseClickedIsland2(MouseEvent e){ handleIslandClick(2); }
    @FXML void mouseClickedIsland3(MouseEvent e){ handleIslandClick(3); }
    @FXML void mouseClickedIsland4(MouseEvent e){ handleIslandClick(4); }
    @FXML void mouseClickedIsland5(MouseEvent e){ handleIslandClick(5); }
    @FXML void mouseClickedIsland6(MouseEvent e){ handleIslandClick(6); }
    @FXML void mouseClickedIsland7(MouseEvent e){ handleIslandClick(7); }
    @FXML void mouseClickedIsland8(MouseEvent e){ handleIslandClick(8); }
    @FXML void mouseClickedIsland9(MouseEvent e){ handleIslandClick(9); }
    @FXML void mouseClickedIsland10(MouseEvent e){ handleIslandClick(10); }
    @FXML void mouseClickedIsland11(MouseEvent e){ handleIslandClick(11); }
    @FXML void mouseClickedIsland12(MouseEvent e){ handleIslandClick(12); }
    @FXML void mouseClickedIsland13(MouseEvent e){ handleIslandClick(13); }
    @FXML void mouseClickedIsland14(MouseEvent e){ handleIslandClick(14); }
    @FXML void mouseClickedIsland15(MouseEvent e){ handleIslandClick(15); }
    @FXML void mouseClickedIsland16(MouseEvent e){ handleIslandClick(16); }
    @FXML void mouseClickedIsland17(MouseEvent e){ handleIslandClick(17); }
    @FXML void mouseClickedIsland18(MouseEvent e){ handleIslandClick(18); }
    @FXML void mouseClickedIsland19(MouseEvent e){ handleIslandClick(19); }
    @FXML void mouseClickedIsland20(MouseEvent e){ handleIslandClick(20); }
    @FXML void mouseClickedIsland21(MouseEvent e){ handleIslandClick(21); }
    @FXML void mouseClickedIsland22(MouseEvent e){ handleIslandClick(22); }
    @FXML void mouseClickedIsland23(MouseEvent e){ handleIslandClick(23); }


    private void handleIslandClick(int vi) {
        if (discardState != DiscardState.NONE) return;
        int me = SessionManager.getInstance().getPlayerId();
        if (me != GameModel.getInstance().getCurrentPlayer()) return;

        // —— Navigator 二段移动 —— （保持原样） ——
        if (skillState == SkillState.NAVI_MOVING_PLAYER) {
            List<Integer> valid = getMoveViewIndicesFor(naviTarget);
            if (valid.contains(vi)) {
                int tile = GameModel.getInstance().getIslandLayout().get(vi);
                System.out.println("[DEBUG][GameController] NAVI move → target=" + naviTarget + ", tile=" + tile);
                CommandManager.getInstance().executeAndSend(new MoveCommand(naviTarget, tile));
                naviMoves--;
                clearHighlights();
                if (naviMoves > 0) {
                    highlightTiles(getMoveViewIndicesFor(naviTarget));
                    Notifier.notify(stage, "Navigator: It can be moved " + naviMoves + " times", "#2196F3");
                } else {
                    skillState = SkillState.NONE;
                    naviTarget = -1;
                }
            }
            return;
        }

        // —— Pilot 飞行 —— （保持原样） ——
        if (currentAction == ActionState.FLY) {
            GameModel model = GameModel.getInstance();
            int tile = model.getIslandLayout().get(vi);
            if (model.getTileState(tile) != SUNK) {
                System.out.println("[DEBUG][GameController] PILOT fly → tile=" + tile);
                CommandManager.getInstance().executeAndSend(new MoveCommand(me, tile));
            }
            clearHighlights();
            currentAction = ActionState.NONE;
            return;
        }

        // —— Engineer 双抽水 ——
        Player self = GameModel.getInstance().getPlayer(me);
        if (currentAction == ActionState.SHOREUP
                && self.getProfession() == Profession.ENGINEER) {
            List<Integer> valid = getShoreUpViewIndices();
            if (!valid.contains(vi)) return;
            engineerShoreTargets.add(vi);
            if (engineerShoreTargets.size() == 1) {
                Notifier.notify(stage, "Engineer: Please select another island to pump. If there is only one island to reinforce, click on it twice", "#2196F3");
            } else {
                List<Integer> tiles = engineerShoreTargets.stream()
                        .map(idx -> GameModel.getInstance().getIslandLayout().get(idx))
                        .collect(Collectors.toList());
                CommandManager.getInstance().executeAndSend(new EngineerShoreUpCommand(me, tiles));
                engineerShoreTargets.clear();
                clearHighlights();
                currentAction = ActionState.NONE;
            }
            return;
        }


        // —— 普通 Move —— （去掉这里的 useActionPoint 调用） ——
        if (currentAction == ActionState.MOVE) {
            List<Integer> valid = getMoveViewIndices();
            if (!valid.contains(vi)) return;
            int tile = GameModel.getInstance().getIslandLayout().get(vi);
            System.out.println("[DEBUG][GameController] 普通 Move → clickedVi=" + vi + ", tile=" + tile);
            // 不再在这里扣AP，AP由 MoveCommand.execute() 统一处理
            CommandManager.getInstance().executeAndSend(new MoveCommand(me, tile));
            clearHighlights();
            currentAction = ActionState.NONE;
            return;
        }

        // —— 普通 ShoreUp —— （保持原样） ——
        if (currentAction == ActionState.SHOREUP) {
            List<Integer> valid = getShoreUpViewIndices();
            if (!valid.contains(vi)) return;
            int tile = GameModel.getInstance().getIslandLayout().get(vi);
            CommandManager.getInstance().executeAndSend(new ShoreUpCommand(tile));
            clearHighlights();
            currentAction = ActionState.NONE;
        }
    }



    // —— 卡牌点击 ——

    @FXML void mouseClicked00(MouseEvent e){ handleCardClick(0); }
    @FXML void mouseClicked01(MouseEvent e){ handleCardClick(1); }
    @FXML void mouseClicked02(MouseEvent e){ handleCardClick(2); }
    @FXML void mouseClicked03(MouseEvent e){ handleCardClick(3); }
    @FXML void mouseClicked04(MouseEvent e){ handleCardClick(4); }

    @FXML void mouseClicked10(MouseEvent e){ handleCardClick(0); }
    @FXML void mouseClicked11(MouseEvent e){ handleCardClick(1); }
    @FXML void mouseClicked12(MouseEvent e){ handleCardClick(2); }
    @FXML void mouseClicked13(MouseEvent e){ handleCardClick(3); }
    @FXML void mouseClicked14(MouseEvent e){ handleCardClick(4); }

    @FXML void mouseClicked20(MouseEvent e){ handleCardClick(0); }
    @FXML void mouseClicked21(MouseEvent e){ handleCardClick(1); }
    @FXML void mouseClicked22(MouseEvent e){ handleCardClick(2); }
    @FXML void mouseClicked23(MouseEvent e){ handleCardClick(3); }
    @FXML void mouseClicked24(MouseEvent e){ handleCardClick(4); }

    @FXML void mouseClicked30(MouseEvent e){ handleCardClick(0); }
    @FXML void mouseClicked31(MouseEvent e){ handleCardClick(1); }
    @FXML void mouseClicked32(MouseEvent e){ handleCardClick(2); }
    @FXML void mouseClicked33(MouseEvent e){ handleCardClick(3); }
    @FXML void mouseClicked34(MouseEvent e){ handleCardClick(4); }

    private void handleCardClick(int ci) {
        int me = SessionManager.getInstance().getPlayerId();
        var hand = GameModel.getInstance().getTreasureHand(me);

        if (discardState == DiscardState.MUST_DISCARD) {
            if (ci >= 0 && ci < hand.size()) {
                var card = hand.get(ci);
                CommandManager.getInstance().sendOnly(new DiscardCommand(me, card));
            }
            discardState = DiscardState.NONE;
            clearHighlights();
            return;
        }

        if (giveState != GiveState.SELECT_CARD) return;
        if (ci < 0 || ci >= hand.size()) return;
        selectedCardIdx = ci;
        cardViewsByPlayer.get(me).forEach(Animations::stopFlash);
        giveState = GiveState.SELECT_PLAYER;
        validTargets.forEach(i -> Animations.startFlash(avatars.get(i)));
    }

    // —— 玩家头像点击 ——

    @FXML void mouseClickedPlayer0(MouseEvent e){ handleAvatarClick(0); }
    @FXML void mouseClickedPlayer1(MouseEvent e){ handleAvatarClick(1); }
    @FXML void mouseClickedPlayer2(MouseEvent e){ handleAvatarClick(2); }
    @FXML void mouseClickedPlayer3(MouseEvent e){ handleAvatarClick(3); }

    private void handleAvatarClick(int target) {
        int me = SessionManager.getInstance().getPlayerId();
        if (discardState != DiscardState.NONE) return;

        // 导航员技能移动玩家
        if (skillState == SkillState.NAVI_SELECT_PLAYER) {
            if (target == me) return;  // 玩家不能选择自己
            naviTarget = target;
            naviMoves  = 2;  // 设置导航员可移动的次数
            skillState = SkillState.NAVI_MOVING_PLAYER;
            clearHighlights();
            highlightTiles(getMoveViewIndicesFor(naviTarget)); // 高亮目标玩家可移动的位置
            Notifier.notify(stage, "Navigator: Please select Move Island（Remain " + naviMoves + " times）", "#2196F3");
            return;
        }

        // 如果是给其他玩家传卡（只有在选卡状态下才能操作）
        if (giveState != GiveState.SELECT_PLAYER) return;
        List<TreasureCardType> myHand     = GameModel.getInstance().getTreasureHand(me);
        List<TreasureCardType> targetHand = GameModel.getInstance().getTreasureHand(target);
        if (selectedCardIdx < 0 || selectedCardIdx >= myHand.size()) {
            giveState = GiveState.NONE;
            clearHighlights();
            return;
        }
        TreasureCardType card = myHand.get(selectedCardIdx);

        // 如果目标玩家手牌已满，不能给卡片
        if (targetHand.size() >= 5) {
            giveState = GiveState.NONE;
            clearHighlights();
            GameModel.getInstance().giveCard(me, target, card);
            CommandManager.getInstance().executeAndSend(new GiveCommand(me, target, card));
            Notifier.notify(stage,
                    target == me ? "When your hand is full, discard a card first" : "Your opponent's hand is full, wait for them to fold",
                    "#f13242");
            if (target == me) {
                discardState = DiscardState.MUST_DISCARD;
                for (ImageView iv : cardViewsByPlayer.get(me)) {
                    Animations.startFlash(iv);
                }
            }
            return;
        }

        giveState = GiveState.NONE;
        clearHighlights();
        GameModel.getInstance().giveCard(me, target, card);
        CommandManager.getInstance().executeAndSend(new GiveCommand(me, target, card));
    }

    private void highlightTiles(List<Integer> indices) {
        clearHighlights();  // 清除所有之前的高亮

        for (int vi : indices) {
            ImageView iv = islands.get(vi);
            DropShadow ds = new DropShadow(15, Color.WHITE);
            ds.setSpread(0.6);
            iv.setEffect(ds);

            Timeline tl = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(iv.scaleXProperty(), 1.0),
                            new KeyValue(iv.scaleYProperty(), 1.0),
                            new KeyValue(ds.radiusProperty(), 15)
                    ),
                    new KeyFrame(Duration.seconds(0.8),
                            new KeyValue(iv.scaleXProperty(), 1.1),
                            new KeyValue(iv.scaleYProperty(), 1.1),
                            new KeyValue(ds.radiusProperty(), 30)
                    )
            );
            tl.setAutoReverse(true);
            tl.setCycleCount(Animation.INDEFINITE);
            tl.play();
            highlightAnimations.put(iv, tl);  // 保存动画状态
        }
    }

    private void clearHighlights() {
        // 停止所有正在播放的高亮动画
        highlightAnimations.values().forEach(Animation::stop);
        highlightAnimations.clear();

        // 移除所有岛屿的特效，并重置缩放
        for (ImageView iv : islands) {
            iv.setEffect(null);
            iv.setScaleX(1.0);
            iv.setScaleY(1.0);
        }

        // 停止所有头像的闪烁
        avatars.forEach(Animations::stopFlash);

        // 停止所有手牌图标的闪烁
        cardViewsByPlayer.forEach(list -> list.forEach(Animations::stopFlash));
    }

    private void updateHighlights() {
        // 如果正在强制弃牌阶段，不清除也不高亮任何岛屿，保留手牌闪烁
        if (discardState == DiscardState.MUST_DISCARD) {
            return;
        }

        // 否则按照原逻辑清除并高亮
        clearHighlights();
        System.out.println("[DEBUG] updateHighlights → skillState=" + skillState + ", currentAction=" + currentAction);
        if (skillState == SkillState.NAVI_MOVING_PLAYER) {
            List<Integer> list = getMoveViewIndicesFor(naviTarget);
            System.out.println("[DEBUG] Navigator 高亮 indices=" + list);
            highlightTiles(list);
        }
        else if (currentAction == ActionState.FLY) {
            List<Integer> list = getFlyViewIndices();
            System.out.println("[DEBUG] Pilot 飞行 高亮 indices=" + list);
            highlightTiles(list);
        }
        else if (currentAction == ActionState.MOVE) {
            List<Integer> list = getMoveViewIndices();
            System.out.println("[DEBUG] 普通 Move 高亮 indices=" + list);
            highlightTiles(list);
        }
        else if (currentAction == ActionState.SHOREUP) {
            List<Integer> list = getShoreUpViewIndices();
            System.out.println("[DEBUG] 普通 ShoreUp 高亮 indices=" + list);
            highlightTiles(list);
        }
    }



    private List<Integer> getFlyViewIndices() {
        GameModel model = GameModel.getInstance();
        List<Integer> out = new ArrayList<>();
        for (int vi = 0; vi < model.getIslandLayout().size(); vi++) {
            int tile = model.getIslandLayout().get(vi);
            if (model.getTileState(tile) != SUNK) out.add(vi);
        }
        return out;
    }

    private List<Integer> getMoveViewIndices() {
        GameModel model = GameModel.getInstance();
        int me = SessionManager.getInstance().getPlayerId();
        Player p = model.getPlayer(me);
        if (p == null) return Collections.emptyList();

        // 把当前玩家所在的 tileIndex 映射到视图索引 startVi
        Integer startVi = tileToView.get(p.getTileIndex());
        if (startVi == null) return Collections.emptyList();

        // 如果不是 Diver，沿用原来的上下左右规则
        if (p.getProfession() != Profession.DIVER) {
            boolean explorer = p.getProfession() == Profession.EXPLORER;
            Integer r0 = GridPane.getRowIndex(islands.get(startVi));
            Integer c0 = GridPane.getColumnIndex(islands.get(startVi));
            if (r0 == null || c0 == null) return Collections.emptyList();

            List<Integer> out = new ArrayList<>();
            for (int vi = 0; vi < islands.size(); vi++) {
                Integer r = GridPane.getRowIndex(islands.get(vi));
                Integer c = GridPane.getColumnIndex(islands.get(vi));
                if (r == null || c == null) continue;
                int dr = Math.abs(r - r0), dc = Math.abs(c - c0);
                boolean ok = explorer ? (Math.max(dr, dc) == 1) : (dr + dc == 1);
                int tile = model.getIslandLayout().get(vi);
                if (ok && model.getTileState(tile) != SUNK) {
                    out.add(vi);
                }
            }
            return out;
        }

        // —— Diver 专属逻辑 ——
        Set<Integer> result = new HashSet<>();

        // 1) 先收集所有上下左右相邻且非 SUNK 的位置
        int r0 = GridPane.getRowIndex(islands.get(startVi));
        int c0 = GridPane.getColumnIndex(islands.get(startVi));
        for (int[] d : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}) {
            int nr = r0 + d[0], nc = c0 + d[1];
            for (int j = 0; j < islands.size(); j++) {
                Integer jr = GridPane.getRowIndex(islands.get(j));
                Integer jc = GridPane.getColumnIndex(islands.get(j));
                if (jr != null && jc != null && jr == nr && jc == nc) {
                    int tile = model.getIslandLayout().get(j);
                    if (model.getTileState(tile) != SUNK) {
                        result.add(j);
                        System.out.println("[DEBUG][DIVER-邻居] 加入相邻 vi=" + j);
                    }
                    break;
                }
            }
        }

        // 2) BFS：可以穿过 FLOODED/SUNK，但只能在 DRY 上落脚
        boolean[] visited = new boolean[islands.size()];
        Deque<Integer> queue = new ArrayDeque<>();
        visited[startVi] = true;
        queue.add(startVi);

        while (!queue.isEmpty()) {
            int vi = queue.poll();
            int r = GridPane.getRowIndex(islands.get(vi));
            int c = GridPane.getColumnIndex(islands.get(vi));

            for (int[] d : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}) {
                int nr = r + d[0], nc = c + d[1];
                for (int j = 0; j < islands.size(); j++) {
                    Integer jr = GridPane.getRowIndex(islands.get(j));
                    Integer jc = GridPane.getColumnIndex(islands.get(j));
                    if (jr != null && jc != null && jr == nr && jc == nc && !visited[j]) {
                        visited[j] = true;
                        int tile = model.getIslandLayout().get(j);
                        GameModel.TileState st = model.getTileState(tile);

                        // 如果是 DRY：可以落脚
                        if (st == DRY) {
                            result.add(j);
                            System.out.println("[DEBUG][DIVER-BFS] 可落脚 vi=" + j);
                        }
                        // 如果是 FLOODED 或 SUNK：可以继续穿越
                        if (st == FLOODED || st == SUNK) {
                            queue.add(j);
                            System.out.println("[DEBUG][DIVER-BFS] 穿越 vi=" + j + " (state=" + st + ")");
                        }
                        break;
                    }
                }
            }
        }

        // 不要把自己也算进去
        result.remove(startVi);
        return new ArrayList<>(result);
    }




    private List<Integer> getShoreUpViewIndices() {
        GameModel model = GameModel.getInstance();
        int me = SessionManager.getInstance().getPlayerId();
        Player p = model.getPlayer(me);
        if (p == null) return Collections.emptyList();
        boolean explorer = p.getProfession() == Profession.EXPLORER;
        Integer meVi = tileToView.get(p.getTileIndex());
        if (meVi == null) return Collections.emptyList();
        Integer r0 = GridPane.getRowIndex(islands.get(meVi));
        Integer c0 = GridPane.getColumnIndex(islands.get(meVi));
        if (r0 == null || c0 == null) return Collections.emptyList();
        List<Integer> out = new ArrayList<>();
        int myTile = model.getIslandLayout().get(meVi);
        if (model.getTileState(myTile) == FLOODED) out.add(meVi);
        for (int vi = 0; vi < islands.size(); vi++) {
            Integer r = GridPane.getRowIndex(islands.get(vi));
            Integer c = GridPane.getColumnIndex(islands.get(vi));
            if (r == null || c == null) continue;
            int dr = Math.abs(r - r0), dc = Math.abs(c - c0);
            boolean ok = explorer ? (Math.max(dr, dc) == 1) : (dr + dc == 1);
            int tile = model.getIslandLayout().get(vi);
            if (ok && model.getTileState(tile) == FLOODED) out.add(vi);
        }
        return out;
    }

    private List<Integer> getMoveViewIndicesFor(int pid) {
        GameModel model = GameModel.getInstance();
        Player p = model.getPlayer(pid);
        if (p == null) return Collections.emptyList();
        Integer startVi = tileToView.get(p.getTileIndex());
        if (startVi == null) return Collections.emptyList();
        Integer r0 = GridPane.getRowIndex(islands.get(startVi));
        Integer c0 = GridPane.getColumnIndex(islands.get(startVi));
        if (r0 == null || c0 == null) return Collections.emptyList();
        List<Integer> out = new ArrayList<>();
        for (int vi = 0; vi < islands.size(); vi++) {
            Integer r = GridPane.getRowIndex(islands.get(vi));
            Integer c = GridPane.getColumnIndex(islands.get(vi));
            if (r == null || c == null) continue;
            int dr = Math.abs(r - r0), dc = Math.abs(c - c0);
            boolean ok;
            switch (p.getProfession()) {
                case EXPLORER: ok = (dr <= 1 && dc <= 1) && (dr + dc > 0); break;
                default:       ok = (dr + dc == 1);
            }
            int tile = model.getIslandLayout().get(vi);
            if (ok && model.getTileState(tile) != SUNK) out.add(vi);
        }
        return out;
    }

    // —— hover 效果 ——

    @FXML void mouseEnteredMove(MouseEvent e)    { move.setOpacity(0.5); }
    @FXML void mouseExitedMove(MouseEvent e)     { move.setOpacity(1);   }
    @FXML void mouseEnteredShoreUp(MouseEvent e) { shoreUp.setOpacity(0.5); }
    @FXML void mouseExitedShoreUp(MouseEvent e)  { shoreUp.setOpacity(1); }
    @FXML void mouseEnteredNextTurn(MouseEvent e){ nextTurn.setOpacity(0.5); }
    @FXML void mouseExitedNextTurn(MouseEvent e) { nextTurn.setOpacity(1); }
    @FXML void mouseEnteredCapture(MouseEvent e) { capture.setOpacity(0.5); }
    @FXML void mouseExitedCapture(MouseEvent e)  { capture.setOpacity(1); }
    @FXML void mouseEnteredGive(MouseEvent e)    { give.setOpacity(0.5); }
    @FXML void mouseExitedGive(MouseEvent e)     { give.setOpacity(1); }
    @FXML void mouseEnteredUseSkill(MouseEvent e){ useSkill.setOpacity(0.5); }
    @FXML void mouseExitedUseSkill(MouseEvent e) { useSkill.setOpacity(1); }
    @FXML void mouseEnteredStart(MouseEvent e)   { startGameButton.setOpacity(0.5); }
    @FXML void mouseExitedStart(MouseEvent e)    { startGameButton.setOpacity(1); }
    @FXML void mouseEnteredExit(MouseEvent e)    { exitButton.setOpacity(0.5); }
    @FXML void mouseExitedExit(MouseEvent e)     { exitButton.setOpacity(1); }

    @FXML private void onStartGame() { CommandManager.getInstance().sendOnly(new StartGameCommand(-1)); }
    @FXML private void onExit()      { Platform.exit(); }

    private int prevWaterLevel = -1;

    @Override
    public void onModelChanged() {
        Platform.runLater(() -> {
            AP.setText("AP: " + GameModel.getInstance().getActionPoints(SessionManager.getInstance().getPlayerId()));
            waterLevel.setText("WaterLevel: " + GameModel.getInstance().getWaterLevel());

            GameModel model = GameModel.getInstance();
            int cur = model.getCurrentPlayer();
            int me  = SessionManager.getInstance().getPlayerId();

            // 回合切换
            if (cur != lastTurnPlayer) {
                if (cur == me) pilotSkillUsed = false;
                skillState  = SkillState.NONE;
                naviTarget  = -1;
                naviMoves   = 0;
                clearHighlights();
                lastTurnPlayer = cur;
            }

            // 更新布局映射
            List<Integer> layout = model.getIslandLayout();
            if (layout.size() != islands.size()) return;
            tileToView.clear();
            for (int i = 0; i < layout.size(); i++) {
                tileToView.put(layout.get(i), i);
            }

            // 渲染岛屿 & 沙子
            for (int vi = 0; vi < layout.size(); vi++) {
                int tid = layout.get(vi);
                GameModel.TileState st = model.getTileState(tid);
                ImageView ivIs = islands.get(vi), ivSd = sands.get(vi);
                if (st == DRY) {
                    ivIs.setImage(ImageFactory.get("islands/island_" + tid + ".png"));
                    ivIs.setVisible(true);  ivSd.setVisible(true);
                } else if (st == FLOODED) {
                    ivIs.setImage(ImageFactory.get("sunkIsland/sunk_island" + tid + ".png"));
                    ivIs.setVisible(true);  ivSd.setVisible(true);
                } else {
                    ivIs.setVisible(false); ivSd.setVisible(false);
                }
            }

            // 渲染玩家 avatar & pawn
            for (ImageView p : pawns) GridPane.clearConstraints(p);
            var pls = model.getPlayers().stream()
                    .sorted(Comparator.comparingInt(Player::getPlayerIndex))
                    .collect(Collectors.toList());
            for (int i = 0; i < avatars.size(); i++) {
                if (i < pls.size()) {
                    Player pp = pls.get(i);
                    avatars.get(i).setImage(ImageFactory.get("playercards/" + pp.getProfession() + ".png"));
                    pawns.get(i).setImage(ImageFactory.get("player/" + pp.getProfession() + ".png"));
                    avatars.get(i).setVisible(true);
                    Integer vi = tileToView.get(pp.getTileIndex());
                    if (vi != null) {
                        ImageView base = islands.get(vi);
                        Integer c = GridPane.getColumnIndex(base),
                                r = GridPane.getRowIndex(base);
                        GridPane.setColumnIndex(pawns.get(i), c == null ? 0 : c);
                        GridPane.setRowIndex(pawns.get(i),    r == null ? 0 : r);
                        pawns.get(i).setVisible(true);
                    }
                } else {
                    avatars.get(i).setVisible(false);
                    pawns.get(i).setVisible(false);
                }
            }

            // 渲染手牌
            for (int pi = 0; pi < cardViewsByPlayer.size(); pi++) {
                var hand = model.getTreasureHand(pi);
                var views = cardViewsByPlayer.get(pi);
                for (int ci = 0; ci < views.size(); ci++) {
                    ImageView cv = views.get(ci);
                    if (hand != null && ci < hand.size()) {
                        cv.setImage(ImageFactory.get("treasureCards/" + hand.get(ci).name().toLowerCase() + ".png"));
                        cv.setVisible(true);
                    } else {
                        cv.setVisible(false);
                    }
                }
            }

            // 手牌超限弃牌
            if (discardState == DiscardState.NONE) {
                var myHand = model.getTreasureHand(me);
                if (myHand.size() > 5) {
                    discardState = DiscardState.MUST_DISCARD;
                    for (ImageView iv : cardViewsByPlayer.get(me)) {
                        Animations.startFlash(iv);
                    }
                    Notifier.notify(stage, "Your hand is full, discard a card first", "#f13242");
                }
            }

            // 重新高亮
            updateHighlights();

            // 按钮状态
            boolean hasAP = model.getActionPoints(me) > 0;
            move.setDisable(!hasAP);
            shoreUp.setDisable(!hasAP);
            useSkill.setDisable(!hasAP || pilotSkillUsed);

            AP.setText("AP: " + GameModel.getInstance().getActionPoints(SessionManager.getInstance().getPlayerId()));
            waterLevel.setText("WaterLevel: " + GameModel.getInstance().getWaterLevel());

            // —— 检测水位是否上涨，如果上涨就弹 Notifier ——
            int newWL = model.getWaterLevel();
            if (newWL > prevWaterLevel) {
                Notifier.notify(stage, "Water Rise! Flood deck shuffle!", "#3498db");
            }
            // 更新 prevWaterLevel
            prevWaterLevel = newWL;
        });
    }
}
