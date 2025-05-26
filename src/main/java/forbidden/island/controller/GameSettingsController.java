package forbidden.island.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameSettingsController {

    @FXML
    private ComboBox<String> playerComboBox;

    @FXML
    private ComboBox<String> modeComboBox;

    @FXML
    private ComboBox<String> turnTimeComboBox;

    @FXML
    private Button startGameButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        // 初始化组合框的数据
        ObservableList<String> players = FXCollections.observableArrayList("2", "3", "4");
        ObservableList<String> modes = FXCollections.observableArrayList("即时制", "回合制");
        ObservableList<String> turnTimes = FXCollections.observableArrayList("不限时间", "慢速", "普通", "快速");

        playerComboBox.setItems(players);
        modeComboBox.setItems(modes);
        turnTimeComboBox.setItems(turnTimes);

        // 初始化时禁用开始游戏按钮
        startGameButton.setDisable(true);

        // 为组合框添加监听器，当所有选项都被选择时启用开始游戏按钮
        playerComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> checkSelection());
        modeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> checkSelection());
        turnTimeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> checkSelection());
    }

    private void checkSelection() {
        // 检查所有选项是否都被选择
        boolean allSelected = playerComboBox.getValue() != null &&
                modeComboBox.getValue() != null &&
                turnTimeComboBox.getValue() != null;

        // 根据选择状态启用或禁用开始游戏按钮
        startGameButton.setDisable(!allSelected);
    }

    @FXML
    private void startGame() {
        // 实现开始游戏的逻辑
        System.out.println("Starting game with settings:");
        System.out.println("Number of Players: " + playerComboBox.getValue());
        System.out.println("Game Mode: " + modeComboBox.getValue());
        System.out.println("Turn Time: " + turnTimeComboBox.getValue());
    }

    @FXML
    private void backToMenu() {
        // 实现返回主菜单的逻辑
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
