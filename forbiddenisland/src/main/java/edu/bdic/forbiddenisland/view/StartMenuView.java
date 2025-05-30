package edu.bdic.forbiddenisland.view;

import edu.bdic.forbiddenisland.util.ImageFactory;
import edu.bdic.forbiddenisland.util.ImageType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 视图类：启动菜单页面加载器
 */
public class StartMenuView {
    public void init(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/edu/bdic/forbiddenisland/view/StartMenuView.fxml"
                    )
            );
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Forbidden Island");
            stage.getIcons().add(ImageFactory.get(ImageType.ICON));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
