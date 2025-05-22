package team.group14.forbiddenisland.service;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Data;
import team.group14.forbiddenisland.controller.LogController;
import team.group14.forbiddenisland.model.*;
import team.group14.forbiddenisland.model.adventurer.*;
import team.group14.forbiddenisland.view.GameScreen;
import team.group14.forbiddenisland.view.SettingScreen;
import team.group14.forbiddenisland.view.StartScreen;

import java.util.*;

@Data
public class Director {

    public static final double WIDTH = 1024, HEIGHT = 1024;

    private static final Director instance = new Director();
    private Stage stage;
    private GameScreen gameScreen = new GameScreen();
    private int playerNum;
    private int gameLevel;
    private List<Player> players = new ArrayList<>();
    // 24 个岛屿板块，组成地图
    private List<IslandTile> islandTiles = new ArrayList<>();
    // 卡牌牌堆与弃牌堆
    private Deck<TreasureCard> treasureDeck;
    private List<TreasureCard> treasureDiscardPile = new ArrayList<>();
    private Deck<FloodCard> floodDeck;
    private List<FloodCard> floodDiscardPile = new ArrayList<>();
    // 水位计
    private WaterMeter waterMeter;
    // 当前游戏状态
    private GameState gameState;

    private Director() {
    }

    public static Director getInstance() {
        return instance;
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Forbidden Island");
        stage.getIcons().add(new Image("E:/Code/JavaCode/ForbiddenIsland/src/main/resources/team/group14/forbiddenisland/images/icon.jpg")); //E:/Code/JavaCode/ForbiddenIsland/src/main/resources/team/group14/forbiddenisland/images/icon.jpg
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        this.stage = stage;
        toStart();
        stage.show();
    }

    public void toStart() {
        StartScreen.load(stage);
        LogController.printLog("Game loading...",
                this.getClass().getSimpleName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void toSet() {
        SettingScreen.load(stage);
        LogController.printLog("Load Setting...",
                this.getClass().getSimpleName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void initGame() {
        initPlayer();
        initWaterMeter();
        initTreasureDeck();
        initFloodDeck();
        initIslandTiles();
        initGameState();
        if (gameState.getTurnNumber() == 1) {
            initTreasureCards();
            initFloodCards();
        }
    }

    private void initIslandTiles() {
        for (int i = 0; i < 24; i++) {
            islandTiles.add(new IslandTile(i));
        }
    }

    private void initFloodCards() {
        for (int i = 0; i < 6; i++) {
            FloodCard floodCard = floodDeck.draw();
            for (IslandTile islandTile : islandTiles) {
                if (islandTile.getId() == floodCard.getIslandName()) {
                    islandTile.flood();
                }
            }
        }
    }

    private void initTreasureCards() {
        for (int i = 0; i < playerNum; i++) {
            for (int j = 0; j < 2; j++) {
                TreasureCard treasureCard = treasureDeck.draw();
                if (treasureCard.getSpecialType() == SpecialCardType.WATERS_RISE) {
                    TreasureCard treasureCardNext = treasureDeck.draw();
                    players.get(i).addCard(treasureCardNext);
                    treasureDeck.addCard(treasureCard);
                    treasureDeck.shuffle();
                } else {
                    players.get(i).addCard(treasureCard);
                }
            }
        }
    }

    private void initWaterMeter() {
        this.waterMeter = new WaterMeter(gameLevel);
    }

    private void initGameState() {
        this.gameState = new GameState();
    }

    private void initTreasureDeck() {
        List<TreasureCard> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new TreasureCard(Treasure.FIRE));
        }
        for (int i = 0; i < 5; i++) {
            list.add(new TreasureCard(Treasure.EARTH));
        }
        for (int i = 0; i < 5; i++) {
            list.add(new TreasureCard(Treasure.OCEAN));
        }
        for (int i = 0; i < 5; i++) {
            list.add(new TreasureCard(Treasure.WIND));
        }
        for (int i = 0; i < 3; i++) {
            list.add(new TreasureCard(SpecialCardType.HELICOPTER_LIFT));
        }
        for (int i = 0; i < 3; i++) {
            list.add(new TreasureCard(SpecialCardType.WATERS_RISE));
        }
        list.add(new TreasureCard(SpecialCardType.SANDBAG));
        list.add(new TreasureCard(SpecialCardType.SANDBAG));
        this.treasureDeck = new Deck<>(list);
    }

    private void initFloodDeck() {
        List<FloodCard> list = new ArrayList<>();
        list.add(new FloodCard(1));
        list.add(new FloodCard(2));
        list.add(new FloodCard(3));
        list.add(new FloodCard(4));
        list.add(new FloodCard(5));
        list.add(new FloodCard(6));
        list.add(new FloodCard(7));
        list.add(new FloodCard(8));
        list.add(new FloodCard(9));
        list.add(new FloodCard(10));
        list.add(new FloodCard(11));
        list.add(new FloodCard(12));
        list.add(new FloodCard(13));
        list.add(new FloodCard(14));
        list.add(new FloodCard(15));
        list.add(new FloodCard(16));
        list.add(new FloodCard(17));
        list.add(new FloodCard(18));
        list.add(new FloodCard(19));
        list.add(new FloodCard(20));
        list.add(new FloodCard(21));
        list.add(new FloodCard(22));
        list.add(new FloodCard(23));
        list.add(new FloodCard(24));
        this.floodDeck = new Deck<>(list);
    }

    private void initPlayer() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Collections.shuffle(list);
        for (int i = 0; i < playerNum; i++) {
            players.add(new Player(switch (list.get(i)) {
                case 1 -> "Explorer";
                case 2 -> "Diver";
                case 3 -> "Pilot";
                case 4 -> "Engineer";
                case 5 -> "Navigator";
                case 6 -> "Messenger";
                default -> throw new IllegalStateException("Unexpected value: " + list.get(i));
            }, switch (list.get(i)) {
                case 1 -> new Explorer();
                case 2 -> new Diver();
                case 3 -> new Pilot();
                case 4 -> new Engineer();
                case 5 -> new Navigator();
                case 6 -> new Messenger();
                default -> throw new IllegalStateException("Unexpected value: " + list.get(i));
            }));
        }
    }

    public void gameOver() {
        toStart();
        LogController.printLog("Game Over!",
                this.getClass().getSimpleName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void gameStart() {
        initGame();
        GameScreen.load(stage);
        LogController.printLog("New Game Start!",
                this.getClass().getSimpleName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void nextTurn() {
        if (gameState.isGameOver()) {
            gameOver();
        }
//        drawTreasureCard(players.get(gameState.getCurrentPlayerIndex()));
//        drawFloodCards();
    }

//    private void drawTreasureCard(Player player) {
//        if (gameState.getPhase().equals("DRAW_TREASURE")) {
//            for (int i = 0; i < 2; i++) {
//                TreasureCard card = treasureDeck.draw();
//                if (card != null) {
//                    player.addCard(card);
//                    if (card.getSpecialType() == SpecialCardType.WATERS_RISE) {
//                        waterMeter.increaseLevel();
//                        floodDeck.shuffleInDiscardPile();
//                    }
//                }
//            }
//            gameState.nextPhase(); // 进入抽洪水牌阶段
//        }
//    }
//
//    private void drawFloodCards() {
//        if (gameState.getPhase().equals("DRAW_FLOOD")) {
//            int floodCount = waterMeter.getFloodDrawCount();
//            for (int i = 0; i < floodCount; i++) {
//                FloodCard floodCard = floodDeck.draw();
//                IslandTile tile = getTileByName(floodCard.getIslandName());
//                if (tile != null) {
//                    tile.flood();
//                }
//            }
//            gameState.nextTurn(); // 进入下一个玩家回合
//        }
//    }

}

