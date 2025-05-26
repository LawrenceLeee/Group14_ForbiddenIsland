package forbidden.island.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RulesController {

    @FXML
    private ImageView ruleImageView;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    private int currentPage = 1;
    private final int totalPages = 8; // 根据实际页面数量调整

    @FXML
    private void initialize() {
        loadPage(1);
        updatePageButtons();
    }

    private void loadPage(int page) {
        String imageResource = "/ruleimages/rulepage" + page + ".png"; // 根据您的图像文件命名规则调整
        ruleImageView.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(imageResource)));
    }

    @FXML
    private void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            loadPage(currentPage);
            updatePageButtons();
        }
    }

    @FXML
    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            loadPage(currentPage);
            updatePageButtons();
        }
    }

    private void updatePageButtons() {
        previousButton.setDisable(currentPage == 1);
        nextButton.setDisable(currentPage == totalPages);
    }

    @FXML
    private void backToMenu() {
        Stage stage = (Stage) previousButton.getScene().getWindow();
        stage.close();
    }
}