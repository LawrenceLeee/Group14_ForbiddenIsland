package edu.bdic.forbiddenisland.view;

import edu.bdic.forbiddenisland.controller.CommandManager;
import edu.bdic.forbiddenisland.controller.RoomEventManager;
import edu.bdic.forbiddenisland.controller.RoomEventListener;
import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.controller.commands.CreateRoomCommand;
import edu.bdic.forbiddenisland.controller.commands.JoinCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class StartMenuController implements RoomEventListener {
    @FXML private Button createGameButton;
    @FXML private Button joinRoomButton;
    @FXML private Button helpButton;
    @FXML private Button exitButton;

    public StartMenuController() {
        RoomEventManager.getInstance().addListener(this);
    }

    @FXML private void initialize() { /* no-op */ }

    @FXML
    private void handleCreateGame() {
        System.out.println("[Client] 点击 CreateGame");
        CommandManager.getInstance().executeAndSend(new CreateRoomCommand());
    }

    @FXML
    private void handleJoinRoom() {
        showBeautifulJoinDialog();
    }

    @FXML private void handleHelp() {
        showBeautifulAlert("请参阅游戏帮助文档或教程。");
    }

    @FXML private void handleExit() {
        Platform.exit();
    }

    /**
     * 自定义创建房间后的弹窗，显示 sessionId，可复制或取消
     */
    private void showRoomCreatedDialog(String sessionId) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.initOwner(createGameButton.getScene().getWindow());
        dialog.setTitle("Room Created");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);

        DialogPane pane = dialog.getDialogPane();
        pane.getStylesheets().add(getClass().getResource("/edu/bdic/forbiddenisland/view/join-dialog.css").toExternalForm());
        pane.getStyleClass().add("beautiful-dialog");

        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));

        Label title = new Label("Room ID Created:");
        title.getStyleClass().add("dialog-title");

        Label idLabel = new Label(sessionId);
        idLabel.getStyleClass().add("dialog-input");

        pane.setContent(content);
        content.getChildren().addAll(title, idLabel);

        ButtonType copyType = new ButtonType("Copy & Join", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        pane.getButtonTypes().setAll(copyType, cancelType);

        Button copyBtn = (Button) pane.lookupButton(copyType);
        Button cancelBtn = (Button) pane.lookupButton(cancelType);
        copyBtn.getStyleClass().add("dialog-button");
        cancelBtn.getStyleClass().add("dialog-button");

        copyBtn.setOnAction(evt -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent contentClip = new ClipboardContent();
            contentClip.putString(sessionId);
            clipboard.setContent(contentClip);
            // 设置 session 并加入
            SessionManager.getInstance().setSession(sessionId, 0, true);
            CommandManager.getInstance().executeAndSend(new JoinCommand(0));
            dialog.close();
        });

        cancelBtn.setOnAction(evt -> dialog.close());

        dialog.showAndWait();
    }

    /** 自定义现代化加入房间对话框 */
    private void showBeautifulJoinDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.initOwner(createGameButton.getScene().getWindow());
        dialog.setTitle("Join Room");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);

        DialogPane pane = dialog.getDialogPane();
        pane.getStylesheets().add(getClass().getResource("/edu/bdic/forbiddenisland/view/join-dialog.css").toExternalForm());
        pane.getStyleClass().add("beautiful-dialog");

        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));

        Label title = new Label("Enter Room ID");
        title.getStyleClass().add("dialog-title");

        TextField input = new TextField();
        input.setPromptText("Room ID");
        input.getStyleClass().add("dialog-input");

        pane.setContent(content);
        content.getChildren().addAll(title, input);

        ButtonType joinType = new ButtonType("Join", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        pane.getButtonTypes().setAll(joinType, cancelType);

        Button joinBtn = (Button) pane.lookupButton(joinType);
        Button cancelBtn = (Button) pane.lookupButton(cancelType);
        joinBtn.getStyleClass().add("dialog-button");
        cancelBtn.getStyleClass().add("dialog-button");

        dialog.setResultConverter(btn -> btn == joinType ? input.getText().trim() : null);

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(sid -> {
            if (!sid.isEmpty()) {
                SessionManager.getInstance().setSession(sid, -1, false);
                System.out.println("[Client] 点击 JoinRoom, sid=" + sid);
                CommandManager.getInstance().executeAndSend(new JoinCommand(-1));
            } else {
                showBeautifulAlert("Room ID cannot be empty.");
            }
        });
    }

    /** 统一风格警告弹窗 */
    private void showBeautifulAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        DialogPane pane = alert.getDialogPane();
        pane.getStylesheets().add(getClass().getResource("/edu/bdic/forbiddenisland/view/join-dialog.css").toExternalForm());
        pane.getStyleClass().add("beautiful-dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // —— 事件回调 —— //

    @Override
    public void onRoomCreated(String sessionId) {
        Platform.runLater(() -> showRoomCreatedDialog(sessionId));
    }

    @Override
    public void onRoomJoined(String sessionId, int playerIndex) {
        RoomEventManager.getInstance().removeListener(this);
        Platform.runLater(() -> {
            SessionManager.getInstance().setSession(sessionId, playerIndex, SessionManager.getInstance().isHost());
            Stage stage = (Stage) createGameButton.getScene().getWindow();
            new GameView().init(stage);
        });
    }

    @Override public void onRoomList(List<String> sessions) {}
    @Override public void onGameStarted() {}

    // 鼠标事件：hover 效果
    @FXML void mouseEnteredCreate(MouseEvent e) { createGameButton.setOpacity(0.5); }
    @FXML void mouseExitedCreate(MouseEvent e) { createGameButton.setOpacity(1); }
    @FXML void mouseEnteredJoin(MouseEvent e)   { joinRoomButton.setOpacity(0.5); }
    @FXML void mouseExitedJoin(MouseEvent e)    { joinRoomButton.setOpacity(1); }
    @FXML void mouseEnteredHelp(MouseEvent e)   { helpButton.setOpacity(0.5); }
    @FXML void mouseExitedHelp(MouseEvent e)    { helpButton.setOpacity(1); }
    @FXML void mouseEnteredExit(MouseEvent e)   { exitButton.setOpacity(0.5); }
    @FXML void mouseExitedExit(MouseEvent e)    { exitButton.setOpacity(1); }
}
