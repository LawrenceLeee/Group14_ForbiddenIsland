package edu.bdic.forbiddenisland.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameDialogUtil {
    public static void showGameOverDialog(Stage parentStage, Runnable onReturnToMenu) {
        Platform.runLater(() -> {
            VBox card = new VBox(18);
            card.setPadding(new Insets(38, 38, 30, 38));
            card.setAlignment(Pos.CENTER);
            card.setStyle("-fx-background-color: rgba(40,40,55,0.97);"
                    + "-fx-background-radius: 24;"
                    + "-fx-effect: dropshadow(gaussian, #232336aa, 18, 0.35, 0, 2);");

            Label title = new Label("Game Over");
            title.setFont(Font.font("Impact", 40));
            title.setTextFill(Color.web("#ff4757"));
            title.setStyle("-fx-letter-spacing: 2px;");

            Label subtitle = new Label("Your team has perished...");
            subtitle.setFont(Font.font("Segoe UI", 20));
            subtitle.setTextFill(Color.web("#f1f2f6"));

            Button menuBtn = new Button("Return to Main Menu");
            menuBtn.setFont(Font.font("Segoe UI Semibold", 18));
            menuBtn.setPrefWidth(240);
            menuBtn.setStyle(
                    "-fx-background-color: linear-gradient(to right, #1e272e, #5352ed);"
                            + "-fx-background-radius: 16;"
                            + "-fx-text-fill: white;"
                            + "-fx-effect: dropshadow(gaussian, #57606f66, 8, 0.28, 0, 1);"
            );
            menuBtn.setOnAction(e -> {
                ((Stage) menuBtn.getScene().getWindow()).close();
                if (onReturnToMenu != null) onReturnToMenu.run();
            });

            card.getChildren().addAll(title, subtitle, menuBtn);

            StackPane root = new StackPane();
            Region overlay = new Region();
            overlay.setStyle("-fx-background-color: rgba(10,10,20,0.65);");
            overlay.setPrefSize(480, 300);
            root.getChildren().addAll(overlay, card);

            Scene scene = new Scene(root, 420, 220);
            Stage dialog = new Stage(StageStyle.TRANSPARENT);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(parentStage);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.setOnCloseRequest(event -> event.consume());
            dialog.show();
        });
    }

    public static void showGameWonDialog(Stage parentStage, Runnable onReturnToMenu) {
        Platform.runLater(() -> {
            VBox card = new VBox(18);
            card.setPadding(new Insets(38, 38, 30, 38));
            card.setAlignment(Pos.CENTER);
            card.setStyle("-fx-background-color: rgba(38,90,130,0.98);"
                    + "-fx-background-radius: 24;"
                    + "-fx-effect: dropshadow(gaussian, #1e5a86bb, 18, 0.35, 0, 2);");

            Label title = new Label("Victory!");
            title.setFont(Font.font("Impact", 40));
            title.setTextFill(Color.web("#2ed573"));
            title.setStyle("-fx-letter-spacing: 2px;");

            Label subtitle = new Label("Your team escaped the Forbidden Island!");
            subtitle.setFont(Font.font("Segoe UI", 20));
            subtitle.setTextFill(Color.web("#f1f2f6"));

            Button menuBtn = new Button("Return to Main Menu");
            menuBtn.setFont(Font.font("Segoe UI Semibold", 18));
            menuBtn.setPrefWidth(240);
            menuBtn.setStyle(
                    "-fx-background-color: linear-gradient(to right, #16a085, #2980b9);"
                            + "-fx-background-radius: 16;"
                            + "-fx-text-fill: white;"
                            + "-fx-effect: dropshadow(gaussian, #57606f66, 8, 0.28, 0, 1);"
            );
            menuBtn.setOnAction(e -> {
                ((Stage) menuBtn.getScene().getWindow()).close();
                if (onReturnToMenu != null) onReturnToMenu.run();
            });

            card.getChildren().addAll(title, subtitle, menuBtn);

            StackPane root = new StackPane();
            Region overlay = new Region();
            overlay.setStyle("-fx-background-color: rgba(10,30,40,0.58);");
            overlay.setPrefSize(480, 300);
            root.getChildren().addAll(overlay, card);

            Scene scene = new Scene(root, 420, 220);
            Stage dialog = new Stage(StageStyle.TRANSPARENT);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(parentStage);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.setOnCloseRequest(event -> event.consume());
            dialog.show();
        });
    }
}
