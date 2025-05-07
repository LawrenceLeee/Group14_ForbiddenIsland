package team.group14.forbiddenisland.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import team.group14.forbiddenisland.service.Director;

public class SettingScreenController {

    @FXML
    private Button finish;

    @FXML
    private Button fourPlayers;

    @FXML
    private Button threePlayers;

    @FXML
    private Button twoPlayers;

    @FXML
    void finishClicked(MouseEvent event) {
        Director.getInstance().toStart();
    }

    @FXML
    void fourPlayersClicked(MouseEvent event) {
        Director.getInstance().setPlayerNum(4);
    }

    @FXML
    void mouseEnteredFinish(MouseEvent event) {
        finish.setOpacity(0.5);
    }

    @FXML
    void mouseEnteredFourPlayers(MouseEvent event) {
        fourPlayers.setOpacity(0.5);
    }

    @FXML
    void mouseEnteredThreePlayers(MouseEvent event) {
        threePlayers.setOpacity(0.5);
    }

    @FXML
    void mouseEnteredTwoPlayers(MouseEvent event) {
        twoPlayers.setOpacity(0.5);
    }

    @FXML
    void mouseExitedFinish(MouseEvent event) {
        finish.setOpacity(1);
    }

    @FXML
    void mouseExitedFourPlayers(MouseEvent event) {
        fourPlayers.setOpacity(1);
    }

    @FXML
    void mouseExitedThreePlayers(MouseEvent event) {
        threePlayers.setOpacity(1);
    }

    @FXML
    void mouseExitedTwoPlayers(MouseEvent event) {
        twoPlayers.setOpacity(1);
    }

    @FXML
    void threePlayersClicked(MouseEvent event) {
        Director.getInstance().setPlayerNum(3);
    }

    @FXML
    void twoPlayersClicked(MouseEvent event) {
        Director.getInstance().setPlayerNum(2);
    }

}

