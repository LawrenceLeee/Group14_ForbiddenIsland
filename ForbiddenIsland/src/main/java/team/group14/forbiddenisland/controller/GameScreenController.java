package team.group14.forbiddenisland.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import team.group14.forbiddenisland.model.ActionState;
import team.group14.forbiddenisland.model.IslandTile;
import team.group14.forbiddenisland.model.Player;
import team.group14.forbiddenisland.model.TileState;
import team.group14.forbiddenisland.service.Director;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreenController {

    private int playerNum = 1;

    private double imageId = 0;

    @FXML
    private Button changeCards;

    @FXML
    private Button exchange;

    @FXML
    private Button move;

    @FXML
    private Button reinforcement;

    @FXML
    private Button skills;

    @FXML
    private ImageView diver;

    @FXML
    private ImageView diverCard;

    @FXML
    private ImageView engineer;

    @FXML
    private ImageView engineerCard;

    @FXML
    private ImageView explorer;

    @FXML
    private ImageView explorerCard;

    @FXML
    private ImageView messenger;

    @FXML
    private ImageView messengerCard;

    @FXML
    private ImageView navigator;

    @FXML
    private ImageView navigatorCard;

    @FXML
    private ImageView pilot;

    @FXML
    private ImageView pilotCard;

    @FXML
    private ImageView playerFourLocation;

    @FXML
    private ImageView playerOneLocation;

    @FXML
    private ImageView playerThreeLocation;

    @FXML
    private ImageView playerTwoLocation;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2; // 工程师出生点

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private ImageView image6; // 探险家出生点

    @FXML
    private ImageView image7;

    @FXML
    private ImageView image8;

    @FXML
    private ImageView image9;

    @FXML
    private ImageView image10; // 飞行员出生点

    @FXML
    private ImageView image11; // 航海家出生点

    @FXML
    private ImageView image12;

    @FXML
    private ImageView image13; // 潜水员出生点

    @FXML
    private ImageView image14;

    @FXML
    private ImageView image15;

    @FXML
    private ImageView image16;

    @FXML
    private ImageView image17;

    @FXML
    private ImageView image18; // 邮递员出生点

    @FXML
    private ImageView image19;

    @FXML
    private ImageView image20;

    @FXML
    private ImageView image21;

    @FXML
    private ImageView image22;

    @FXML
    private ImageView image23;

    @FXML
    private ImageView image24;

    @FXML
    private ImageView floodimage1;

    @FXML
    private ImageView floodimage10;

    @FXML
    private ImageView floodimage11;

    @FXML
    private ImageView floodimage12;

    @FXML
    private ImageView floodimage13;

    @FXML
    private ImageView floodimage14;

    @FXML
    private ImageView floodimage15;

    @FXML
    private ImageView floodimage16;

    @FXML
    private ImageView floodimage17;

    @FXML
    private ImageView floodimage18;

    @FXML
    private ImageView floodimage19;

    @FXML
    private ImageView floodimage2;

    @FXML
    private ImageView floodimage20;

    @FXML
    private ImageView floodimage21;

    @FXML
    private ImageView floodimage22;

    @FXML
    private ImageView floodimage23;

    @FXML
    private ImageView floodimage24;

    @FXML
    private ImageView floodimage3;

    @FXML
    private ImageView floodimage4;

    @FXML
    private ImageView floodimage5;

    @FXML
    private ImageView floodimage6;

    @FXML
    private ImageView floodimage7;

    @FXML
    private ImageView floodimage8;

    @FXML
    private ImageView floodimage9;

    @FXML
    private Button nextTurn;

    private BooleanProperty buttonClicked = new SimpleBooleanProperty(false);


    private final List<ImageView> imageViews = new ArrayList<>();
    private final List<ImageView> floodImages = new ArrayList<>();
    private final List<Double[]> positions = new ArrayList<>();
    private final List<Double[]> shuffledPositions = new ArrayList<>();
    private final int[] intShuffledPositions = new int[24];
    private int index = 0;

    @FXML
    public void initialize() {
        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);
        imageViews.add(image6);
        imageViews.add(image7);
        imageViews.add(image8);
        imageViews.add(image9);
        imageViews.add(image10);
        imageViews.add(image11);
        imageViews.add(image12);
        imageViews.add(image13);
        imageViews.add(image14);
        imageViews.add(image15);
        imageViews.add(image16);
        imageViews.add(image17);
        imageViews.add(image18);
        imageViews.add(image19);
        imageViews.add(image20);
        imageViews.add(image21);
        imageViews.add(image22);
        imageViews.add(image23);
        imageViews.add(image24);
        floodImages.add(floodimage1);
        floodImages.add(floodimage2);
        floodImages.add(floodimage3);
        floodImages.add(floodimage4);
        floodImages.add(floodimage5);
        floodImages.add(floodimage6);
        floodImages.add(floodimage7);
        floodImages.add(floodimage8);
        floodImages.add(floodimage9);
        floodImages.add(floodimage10);
        floodImages.add(floodimage11);
        floodImages.add(floodimage12);
        floodImages.add(floodimage13);
        floodImages.add(floodimage14);
        floodImages.add(floodimage15);
        floodImages.add(floodimage16);
        floodImages.add(floodimage17);
        floodImages.add(floodimage18);
        floodImages.add(floodimage19);
        floodImages.add(floodimage20);
        floodImages.add(floodimage21);
        floodImages.add(floodimage22);
        floodImages.add(floodimage23);
        floodImages.add(floodimage24);

        for (ImageView imageView : imageViews) {
            positions.add(new Double[]{imageView.getLayoutX(), imageView.getLayoutY(), imageId});
            imageId++;
        }

        shuffledPositions.addAll(positions);
        Collections.shuffle(shuffledPositions);

        List<IslandTile> list = new ArrayList<>();
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setLayoutX(shuffledPositions.get(i)[0]);
            imageViews.get(i).setLayoutY(shuffledPositions.get(i)[1]);
            floodImages.get(i).setLayoutX(shuffledPositions.get(i)[0]);
            floodImages.get(i).setLayoutY(shuffledPositions.get(i)[1]);
            for (int j = 0; j < imageViews.size(); j++) {
                if (shuffledPositions.get(j)[2] == i) {
                    list.add(Director.getInstance().getIslandTiles().get((int)Math.round(shuffledPositions.get(j)[2])));
                }
            }
        }
        Director.getInstance().setIslandTiles(list);

        for (Double[] doubles : shuffledPositions) {
            intShuffledPositions[index] = (int)doubles[2].doubleValue();
            Director.getInstance().getIslandTiles().get(index).setPlace(intShuffledPositions[index]);
            index++;
        }

        for (int i = 0; i < 24; i++) {
            if (Director.getInstance().getIslandTiles().get(i).getState() == TileState.FLOODED) {
                floodImages.get(i).setOpacity(1);
            }
        }

        engineer.setLayoutX(image2.getLayoutX() + 29);
        engineer.setLayoutY(image2.getLayoutY() + 15);
        explorer.setLayoutX(image6.getLayoutX() + 29);
        explorer.setLayoutY(image6.getLayoutY() + 15);
        pilot.setLayoutX(image10.getLayoutX() + 29);
        pilot.setLayoutY(image10.getLayoutY() + 15);
        navigator.setLayoutX(image11.getLayoutX() + 29);
        navigator.setLayoutY(image11.getLayoutY() + 15);
        diver.setLayoutX(image13.getLayoutX() + 29);
        diver.setLayoutY(image13.getLayoutY() + 15);
        messenger.setLayoutX(image18.getLayoutX() + 29);
        messenger.setLayoutY(image18.getLayoutY() + 15);

        initPlayer();

        // 监听 buttonClicked 变量的变化
        buttonClicked.addListener((BooleanProperty, oldValue, newValue) -> {
            if (!oldValue && newValue) {  // 只在 false -> true 时触发
                buttonClicked.set(false);
                System.out.println("本回合结束, 执行已经输入的逻辑");
                Director.getInstance().nextTurn();
            }
        });
    }

    private void setPlayer(ImageView player, ImageView playerCard) {
        player.setOpacity(1);
        if (playerNum == 1) {
            playerCard.setLayoutX(playerOneLocation.getLayoutX());
            playerCard.setLayoutY(playerOneLocation.getLayoutY());
            playerCard.setOpacity(1);
            this.playerNum++;
        } else if (playerNum == 2) {
            playerCard.setLayoutX(playerTwoLocation.getLayoutX());
            playerCard.setLayoutY(playerTwoLocation.getLayoutY());
            playerCard.setOpacity(1);
            this.playerNum++;
        } else if (playerNum == 3) {
            playerCard.setLayoutX(playerThreeLocation.getLayoutX());
            playerCard.setLayoutY(playerThreeLocation.getLayoutY());
            playerCard.setOpacity(1);
            this.playerNum++;
        } else {
            playerCard.setLayoutX(playerFourLocation.getLayoutX());
            playerCard.setLayoutY(playerFourLocation.getLayoutY());
            playerCard.setOpacity(1);
            this.playerNum++;
        }
    }

    private void initPlayer() {
        for (Player player : Director.getInstance().getPlayers()) {
            switch (player.getAdventurer().getRoleName()) {
                case "Explorer" -> {
                    setPlayer(explorer, explorerCard);
                    for (IslandTile islandTile:Director.getInstance().getIslandTiles()) {
                        if (islandTile.getId() == 5) {
                            player.setCurrentTile(islandTile);
                        }
                    }
                }
                case "Diver" -> {
                    setPlayer(diver, diverCard);
                    for (IslandTile islandTile:Director.getInstance().getIslandTiles()) {
                        if (islandTile.getId() == 12) {
                            player.setCurrentTile(islandTile);
                        }
                    }
                }
                case "Pilot" -> {
                    setPlayer(pilot, pilotCard);
                    for (IslandTile islandTile:Director.getInstance().getIslandTiles()) {
                        if (islandTile.getId() == 9) {
                            player.setCurrentTile(islandTile);
                        }
                    }
                }
                case "Engineer" -> {
                    setPlayer(engineer, engineerCard);
                    for (IslandTile islandTile:Director.getInstance().getIslandTiles()) {
                        if (islandTile.getId() == 1) {
                            player.setCurrentTile(islandTile);
                        }
                    }
                }
                case "Navigator" -> {
                    setPlayer(navigator, navigatorCard);
                    for (IslandTile islandTile:Director.getInstance().getIslandTiles()) {
                        if (islandTile.getId() == 10) {
                            player.setCurrentTile(islandTile);
                        }
                    }
                }
                case "Messenger" -> {
                    setPlayer(messenger, messengerCard);
                    for (IslandTile islandTile:Director.getInstance().getIslandTiles()) {
                        if (islandTile.getId() == 17) {
                            player.setCurrentTile(islandTile);
                        }
                    }
                }
            }
        }
    }

    @FXML
    void mouseClickedImage10(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片10, 玩家位置在" + place);
            if (intShuffledPositions[9] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            } else if (intShuffledPositions[9] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(9));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage1(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片1, 玩家位置在" + place);
            if (intShuffledPositions[0] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            } else if (intShuffledPositions[0] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(0));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage11(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片11, 玩家位置在" + place);
            if (intShuffledPositions[10] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            } else if (intShuffledPositions[10] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(10));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage12(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片12, 玩家位置在" + place);
            if (intShuffledPositions[11] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            } else if (intShuffledPositions[11] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(11));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage13(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片13, 玩家位置在" + place);
            if (intShuffledPositions[12] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            } else if (intShuffledPositions[12] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(12));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage14(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片14, 玩家位置在" + place);
            if (intShuffledPositions[13] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            } else if (intShuffledPositions[13] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(13));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage15(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片15, 玩家位置在" + place);
            if (intShuffledPositions[14] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
                }
            } else if (intShuffledPositions[14] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                }
                Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(14));
            }
        }
    }

    @FXML
    void mouseClickedImage16(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片16, 玩家位置在" + place);
            if (intShuffledPositions[15] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
                }
            } else if (intShuffledPositions[15] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                }
                Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(15));
            }
        }
    }

    @FXML
    void mouseClickedImage17(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片17, 玩家位置在" + place);
            if (intShuffledPositions[16] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
                }
            } else if (intShuffledPositions[16] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                }
                Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(16));
            }
        }
    }

    @FXML
    void mouseClickedImage18(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片18, 玩家位置在" + place);
            if (intShuffledPositions[17] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
                }
            } else if (intShuffledPositions[17] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                }
                Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(17));
            }
        }
    }

    @FXML
    void mouseClickedImage19(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片19, 玩家位置在" + place);
            if (intShuffledPositions[18] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            } else if (intShuffledPositions[18] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(18));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage2(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片2, 玩家位置在" + place);
            if (intShuffledPositions[1] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            } else if (intShuffledPositions[1] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(1));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage20(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片20, 玩家位置在" + place);
            if (intShuffledPositions[19] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            } else if (intShuffledPositions[19] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(19));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage21(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片21, 玩家位置在" + place);
            if (intShuffledPositions[20] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            } else if (intShuffledPositions[20] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(20));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage22(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片22, 玩家位置在" + place);
            if (intShuffledPositions[21] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            } else if (intShuffledPositions[21] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(21));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage23(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片23, 玩家位置在" + place);
            if (intShuffledPositions[22] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            } else if (intShuffledPositions[22] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(22));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage24(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片24, 玩家位置在" + place);
            if (intShuffledPositions[23] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            } else if (intShuffledPositions[23] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(23));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage3(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片3, 玩家位置在" + place);
            if (intShuffledPositions[2] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            } else if (intShuffledPositions[2] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(2));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage4(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片4, 玩家位置在" + place);
            if (intShuffledPositions[3] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            } else if (intShuffledPositions[3] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(3));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage5(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片5, 玩家位置在" + place);
            if (intShuffledPositions[4] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            } else if (intShuffledPositions[4] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(4));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage6(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片6, 玩家位置在" + place);
            if (intShuffledPositions[5] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            } else if (intShuffledPositions[5] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(5));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage7(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片7, 玩家位置在" + place);
            if (intShuffledPositions[6] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            } else if (intShuffledPositions[6] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(6));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage8(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片8, 玩家位置在" + place);
            if (intShuffledPositions[7] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            } else if (intShuffledPositions[7] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(7));
                }
            }
        }
    }

    @FXML
    void mouseClickedImage9(MouseEvent event) {
        if (Director.getInstance().getGameState().getPhase().equals("ACTION") && Director.getInstance().getGameState().getActionState() == ActionState.MOVE) {
            int place = Director.getInstance().getGameState().getCurrentPlayer().getCurrentTile().getPlace();
            String name = Director.getInstance().getGameState().getCurrentPlayer().getName();
            System.out.println("点击图片9, 玩家位置在" + place);
            if (intShuffledPositions[8] == 0) {
                if (place == 1 || place == 3) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 1) {
                if (place == 0 || place == 4) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(62);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(62);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(62);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(62);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(62);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(62);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 2) {
                if (place == 3 || place == 7) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 3) {
                if (place == 0 || place == 2 || place == 4 || place == 8) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 4) {
                if (place == 1 || place == 3 || place == 5 || place == 9) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 5) {
                if (place == 4 || place == 10) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(182);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(182);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(182);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(182);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(182);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(182);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 6) {
                if (place == 7 || place == 12) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 7) {
                if (place == 2 || place == 6 || place == 8 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 8) {
                if (place == 3 || place == 7 || place == 9 || place == 14) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 9) {
                if (place == 4 || place == 8 || place == 10 || place == 15) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 10) {
                if (place == 5 || place == 9 || place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 11) {
                if (place == 10 || place == 17) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(302);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(302);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(302);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(302);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(302);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(302);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 12) {
                if (place == 6 || place == 13) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(186);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(186);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(186);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(186);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(186);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(186);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 13) {
                if (place == 7 || place == 12 || place == 14 || place == 18) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 14) {
                if (place == 8 || place == 13 || place == 15 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 15) {
                if (place == 9 || place == 14 || place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 16) {
                if (place == 10 || place == 15 || place == 17 || place == 21) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 17) {
                if (place == 11 || place == 16) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(786);
                            explorer.setLayoutY(422);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(786);
                            diver.setLayoutY(422);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(786);
                            pilot.setLayoutY(422);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(786);
                            engineer.setLayoutY(422);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(786);
                            navigator.setLayoutY(422);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(786);
                            messenger.setLayoutY(422);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 18) {
                if (place == 13 || place == 19) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(306);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(306);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(306);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(306);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(306);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(306);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 19) {
                if (place == 14 || place == 18 || place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 20) {
                if (place == 15 || place == 19 || place == 21 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 21) {
                if (place == 16 || place == 20) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(666);
                            explorer.setLayoutY(542);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(666);
                            diver.setLayoutY(542);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(666);
                            pilot.setLayoutY(542);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(666);
                            engineer.setLayoutY(542);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(666);
                            navigator.setLayoutY(542);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(666);
                            messenger.setLayoutY(542);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 22) {
                if (place == 19 || place == 23) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(426);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(426);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(426);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(426);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(426);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(426);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            } else if (intShuffledPositions[8] == 23) {
                if (place == 20 || place == 22) {
                    switch (name) {
                        case "Explorer" -> {
                            explorer.setLayoutX(546);
                            explorer.setLayoutY(662);
                        }
                        case "Diver" -> {
                            diver.setLayoutX(546);
                            diver.setLayoutY(662);
                        }
                        case "Pilot" -> {
                            pilot.setLayoutX(546);
                            pilot.setLayoutY(662);
                        }
                        case "Engineer" -> {
                            engineer.setLayoutX(546);
                            engineer.setLayoutY(662);
                        }
                        case "Navigator" -> {
                            navigator.setLayoutX(546);
                            navigator.setLayoutY(662);
                        }
                        case "Messenger" -> {
                            messenger.setLayoutX(546);
                            messenger.setLayoutY(662);
                        }
                    }
                    Director.getInstance().getGameState().getCurrentPlayer().setCurrentTile(Director.getInstance().getIslandTiles().get(8));
                }
            }
        }
    }

    @FXML
    void mouseClickedChangeCards(MouseEvent event) {
        Director.getInstance().getGameState().setActionState(ActionState.CHANGECARDS);
    }

    @FXML
    void mouseClickedExchange(MouseEvent event) {
        Director.getInstance().getGameState().setActionState(ActionState.EXCHANGE);
    }

    @FXML
    void mouseClickedMove(MouseEvent event) {
        Director.getInstance().getGameState().setActionState(ActionState.MOVE);
    }

    @FXML
    void mouseClickedReinforcement(MouseEvent event) {
        Director.getInstance().getGameState().setActionState(ActionState.REINFORCEMENT);
    }

    @FXML
    void mouseClickedSkills(MouseEvent event) {
        Director.getInstance().getGameState().setActionState(ActionState.SKILLS);
    }

    @FXML
    void toNextTurn(ActionEvent event) {
        buttonClicked.set(true);  // 触发监听
    }
}
