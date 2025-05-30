package edu.bdic.forbiddenisland.util;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * 现代化顶部通知工具类，支持自定义背景色与宽度
 */
public class Notifier {

    // 默认配置
    private static final double DEFAULT_WIDTH = 400;
    private static final String DEFAULT_BG_COLOR = "rgba(50,50,50,0.9)";

    /**
     * 弹出通知（默认宽度 & 默认背景色）
     */
    public static void notify(Window owner, String message) {
        notify(owner, message, DEFAULT_BG_COLOR, DEFAULT_WIDTH);
    }

    /**
     * 弹出通知（默认宽度 & 自定义背景色）
     * @param bgColor CSS 格式的背景色，如 "rgba(100,150,200,0.9)" 或 "#336699"
     */
    public static void notify(Window owner, String message, String bgColor) {
        notify(owner, message, bgColor, DEFAULT_WIDTH);
    }

    /**
     * 弹出通知（自定义宽度 & 自定义背景色）
     * @param owner   弹窗的 owner Window，传 null 会居中到屏幕
     * @param message 要显示的文本
     * @param bgColor CSS 格式的背景色
     * @param width   通知框的宽度（px）
     */
    public static void notify(Window owner, String message, String bgColor, double width) {
        Platform.runLater(() -> {
            // 1) 准备 Popup
            Popup popup = new Popup();
            popup.setAutoFix(true);
            popup.setAutoHide(true);

            // 2) 构建内容
            Label label = new Label(message);
            label.setWrapText(true);
            label.setMaxWidth(width - 40); // 留出左右 padding
            label.setTextFill(Color.WHITE);
            label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            label.setAlignment(Pos.CENTER);             // 文本居中
            label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

            StackPane root = new StackPane(label);
            root.setAlignment(Pos.CENTER);              // 子节点（Label）居中
            root.setPadding(new Insets(15, 20, 15, 20));
            root.setStyle(
                    String.format(
                            "-fx-background-color: %s; -fx-background-radius: 8px;",
                            bgColor
                    )
            );
            root.setEffect(new DropShadow(12, Color.BLACK));
            root.setOpacity(0);
            root.setTranslateY(-60);

            // 强制宽度
            root.setPrefWidth(width);
            popup.getContent().add(root);

            // 3) 计算显示位置：水平居中，距顶 20px
            double screenW = Screen.getPrimary().getBounds().getWidth();
            double x = (screenW - width) / 2;
            double y = 20;
            if (owner != null) {
                x = owner.getX() + (owner.getWidth() - width) / 2;
                y = owner.getY() + 20;
            }

            popup.show(owner, x, y);

            // 4) 淡入 + 滑入
            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(root.opacityProperty(),    0, Interpolator.EASE_OUT),
                            new KeyValue(root.translateYProperty(), -60, Interpolator.EASE_OUT)
                    ),
                    new KeyFrame(Duration.millis(500),
                            new KeyValue(root.opacityProperty(),    1, Interpolator.EASE_OUT),
                            new KeyValue(root.translateYProperty(),   0, Interpolator.EASE_OUT)
                    )
            );

            // 5) 停留
            PauseTransition stay = new PauseTransition(Duration.seconds(2.5));

            // 6) 淡出 + 滑出
            Timeline fadeOut = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(root.opacityProperty(),    1, Interpolator.EASE_IN),
                            new KeyValue(root.translateYProperty(),   0, Interpolator.EASE_IN)
                    ),
                    new KeyFrame(Duration.millis(500),
                            new KeyValue(root.opacityProperty(),    0, Interpolator.EASE_IN),
                            new KeyValue(root.translateYProperty(), -60, Interpolator.EASE_IN)
                    )
            );
            fadeOut.setOnFinished(e -> popup.hide());

            // 7) 串联播放
            SequentialTransition seq = new SequentialTransition(fadeIn, stay, fadeOut);
            seq.play();
        });
    }
}
