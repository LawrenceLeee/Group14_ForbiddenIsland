package forbidden.island.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Node;

public class GameOptionsController {

    @FXML
    private Button studyButton;

    @FXML
    private Button startGameButton;

    @FXML
    private javafx.scene.control.Button backButton;

    @FXML
    private void showStudy() {
        // 实现显示学习教程的逻辑
        System.out.println("Study clicked!");
    }

    @FXML
    private void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameSetting.fxml"));
            Scene gameSettingsScene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Game Settings");
            stage.setScene(gameSettingsScene);
            stage.setWidth(1024);
            stage.setHeight(1024);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backToMenu() {
        // 实现返回主菜单的逻辑
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        System.out.println("Back to Menu clicked!");
    }
}