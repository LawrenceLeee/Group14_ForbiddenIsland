package team.group14.forbiddenisland.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import team.group14.forbiddenisland.service.Director;


public class StartScreenController {

    @FXML
    private ImageView startGame;

    @FXML
    private ImageView exit;

    @FXML
    private ImageView settings;

    @FXML
    void mouseClickedExit(MouseEvent event) {
        LogController.printLog("Game Exit!",
                this.getClass().getSimpleName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.exit(1);
    }

    @FXML
    void mouseClickedSettings(MouseEvent event) {
        Director.getInstance().toSet();
    }

    @FXML
    void mouseClickedStarGame(MouseEvent event) {
        Director.getInstance().gameStart();
    }

    @FXML
    void mouseEnteredExit(MouseEvent event) {
        exit.setOpacity(1);
    }

    @FXML
    void mouseEnteredSettings(MouseEvent event) {
        settings.setOpacity(1);
    }

    @FXML
    void mouseEnteredStarGame(MouseEvent event) {
        startGame.setOpacity(1);
    }

    @FXML
    void mouseExistedExit(MouseEvent event) {
       exit.setOpacity(0.5);
    }

    @FXML
    void mouseExistedSettings(MouseEvent event) {
        settings.setOpacity(0.5);
    }

    @FXML
    void mouseExistedStarGame(MouseEvent event) {
        startGame.setOpacity(0.5);
    }

}

