package edu.bdic.forbiddenisland;

import edu.bdic.forbiddenisland.network.MessageType;
import edu.bdic.forbiddenisland.network.handler.MessageHandlerFactory;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import edu.bdic.forbiddenisland.view.StartMenuView;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.NetworkManager;
import edu.bdic.forbiddenisland.controller.CommandManager;

public class MainApp extends Application {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;

    @Override
    public void start(Stage primaryStage) {
        // 在你的 Application start() 方法里
        Font.loadFont(getClass().getResourceAsStream("/fonts/GreatVibes-Regular.ttf"), 28);
        // 1. 建立到服务器的连接，并设置收到消息时的回调
        NetworkManager.getInstance().connect(
                SERVER_HOST,
                SERVER_PORT,
                this::onServerMessage
        );
        // 2. 启动 JavaFX 界面
        StartMenuView view = new StartMenuView();
        view.init(primaryStage);
    }

    /**
     * 收到服务器消息后的统一处理，交给 CommandManager
     */
    private void onServerMessage(Message msg) {
        MessageType type = msg.getType();

        // 只有下面这些才是真正的游戏命令，需要同步到本地模型
        switch (type) {
            case JOIN:
            case MOVE:
            case SHORE_UP:
            case UPDATE_TILE:
            case START:
                CommandManager.getInstance().handleRemoteMessage(msg);
                break;
            default:
                // ROOM_CREATED, ROOM_JOINED, ROOM_LIST, etc. → 不走命令执行
                break;
        }

        // 不论命令还是事件，都需要分发给对应的 Handler
        MessageHandlerFactory.getHandler(type).handle(msg);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
