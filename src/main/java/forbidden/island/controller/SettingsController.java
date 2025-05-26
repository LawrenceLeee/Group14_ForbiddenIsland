package forbidden.island.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private ComboBox<String> mapComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        // 初始化组合框的数据
        ObservableList<String> difficulties = FXCollections.observableArrayList("新手", "标准", "精英", "传奇");
        ObservableList<String> maps = FXCollections.observableArrayList("1", "2", "3", "4");

        difficultyComboBox.setItems(difficulties);
        mapComboBox.setItems(maps);
    }

    @FXML
    private void saveSettings() {
        // 实现保存设置的逻辑
        System.out.println("Saving settings:");
        System.out.println("Difficulty: " + difficultyComboBox.getValue());
        System.out.println("Island Map: " + mapComboBox.getValue());
    }

    @FXML
    private void backToMenu() {
        // 实现返回主菜单的逻辑
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}