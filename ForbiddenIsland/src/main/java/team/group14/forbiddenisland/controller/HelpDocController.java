package team.group14.forbiddenisland.controller;

import javafx.fxml.FXML;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.Setter;
import netscape.javascript.JSObject;

public class HelpDocController {
    @FXML
    private WebView webView;

    private Stage helpStage;
    private Stage mainStage;

    public void init(Stage helpStage, Stage mainStage) {
        this.helpStage = helpStage;
        this.mainStage = mainStage;
    }

    @FXML
    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/team/group14/forbiddenisland/fxml/help.html").toExternalForm());

        // ✅ JS调用桥接对象
        webEngine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("javaBridge", this);
        });
    }

    // JavaScript调用的方法
    public void goBackToMain() {
        if (helpStage != null) {
            helpStage.hide();  // ✅ 不使用 close()，而是 hide()，避免释放窗口资源
        }
        if (mainStage != null) {
            mainStage.show(); // ✅ 原窗口立即显现，不重新加载 FXML
        }
    }
}