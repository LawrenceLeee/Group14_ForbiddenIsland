package edu.bdic.forbiddenisland.view;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpDocController {
    @FXML
    private WebView webView;

    @FXML
    public void initialize() {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/edu/bdic/forbiddenisland/view/help.html").toExternalForm());
    }
}