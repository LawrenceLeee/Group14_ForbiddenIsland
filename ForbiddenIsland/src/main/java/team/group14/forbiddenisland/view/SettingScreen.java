package team.group14.forbiddenisland.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingScreen {
    public static void load(Stage stage) {
        try {
            Parent root = FXMLLoader.load(StartScreen.class.getResource("/team/group14/forbiddenisland/fxml/settingscreen.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
