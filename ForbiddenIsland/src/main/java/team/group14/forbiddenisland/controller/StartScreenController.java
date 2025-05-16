package team.group14.forbiddenisland.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import team.group14.forbiddenisland.service.Director;



public class StartScreenController {

    @FXML
    private ImageView startGame;

    @FXML
    private ImageView exit;

    @FXML
    private ImageView settings;

    @FXML
    private ImageView help;

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

    @FXML
    private void handleHelpHtmlClick() {
        try {
            // 加载 help.fxml 中的 WebView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/team/group14/forbiddenisland/fxml/help.fxml"));
            Parent root = loader.load();

            Stage helpStage = new Stage();
            HelpDocController controller = loader.getController();

            // 从任意节点获取当前主窗口 stage
            Stage mainStage = (Stage) help.getScene().getWindow();

            controller.init(helpStage, mainStage);

            Scene scene = new Scene(root);
            helpStage.setScene(scene);
            helpStage.setTitle("Help Document");
            helpStage.setResizable(true);
            helpStage.show();

            // 隐藏主窗口
            mainStage.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

