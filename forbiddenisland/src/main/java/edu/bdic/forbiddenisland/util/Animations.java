// src/main/java/edu/bdic/forbiddenisland/util/Animations.java
package edu.bdic.forbiddenisland.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class Animations {
    // 存储正在闪烁的 ImageView 对应的 Timeline
    private static final Map<ImageView, Timeline> flashes = new HashMap<>();

    /**
     * 对 ImageView 启动闪烁动画（无限循环、来回淡入淡出）。
     * 如果已经在闪烁，则不重复启动。
     */
    public static void startFlash(ImageView iv) {
        if (flashes.containsKey(iv)) return;
        Timeline tl = new Timeline(
                new KeyFrame(Duration.ZERO,    new KeyValue(iv.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(iv.opacityProperty(), 0.3))
        );
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();
        flashes.put(iv, tl);
    }

    /**
     * 停止闪烁动画，并将透明度恢复到 1.0。
     */
    public static void stopFlash(ImageView iv) {
        Timeline tl = flashes.remove(iv);
        if (tl != null) {
            tl.stop();
            iv.setOpacity(1.0);
        }
    }
}
