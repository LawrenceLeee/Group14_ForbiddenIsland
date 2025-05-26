package forbidden.island.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button startButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    @FXML
    private void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameOptions.fxml"));
            Scene gameOptionsScene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Game Options");
            stage.setScene(gameOptionsScene);
            stage.setWidth(1024);
            stage.setHeight(1024);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
            Scene settingsScene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(settingsScene);
            stage.setWidth(1024);
            stage.setHeight(1024);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showRules() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Rules.fxml"));
            Scene rulesScene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Rules");
            stage.setScene(rulesScene);
            stage.setWidth(1024);
            stage.setHeight(1024);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitGame() {
        // 实现退出游戏的逻辑
        System.out.println("Exit clicked!");
        System.exit(0);
    }
}