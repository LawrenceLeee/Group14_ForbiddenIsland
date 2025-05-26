package forbidden.island;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        // 设置窗口大小为 1024x1024
        primaryStage.setWidth(1024);
        primaryStage.setHeight(1024);

        // 禁用窗口调整大小功能
        primaryStage.setResizable(false);

        primaryStage.setTitle("Forbidden Island");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
