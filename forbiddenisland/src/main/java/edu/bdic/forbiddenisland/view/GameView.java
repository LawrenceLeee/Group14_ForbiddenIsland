package edu.bdic.forbiddenisland.view;

import edu.bdic.forbiddenisland.util.ImageFactory;
import edu.bdic.forbiddenisland.util.ImageType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class GameView {
    public void init(Stage stage) {
        try {
            URL fxmlUrl = getClass().getResource(
                    "/edu/bdic/forbiddenisland/view/GameView.fxml"
            );
            if (fxmlUrl == null) {
                throw new RuntimeException("Can not find GameView.fxml");
            }
            Parent root = FXMLLoader.load(fxmlUrl);

            Scene scene = new Scene(root);
            stage.setTitle("Forbidden Island");
            stage.getIcons().add(ImageFactory.get(ImageType.ICON));
            stage.setScene(scene);
            stage.show();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            javafx.application.Platform.exit();
        }
    }
}
