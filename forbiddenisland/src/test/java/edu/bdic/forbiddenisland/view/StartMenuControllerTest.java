package edu.bdic.forbiddenisland.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 StartMenuController 中的按钮 hover 效果。
 *
 * 为了能在单元测试环境中创建 JavaFX 按钮，需要先显式初始化 JavaFX Toolkit。
 */
public class StartMenuControllerTest {

    private StartMenuController controller;

    @BeforeAll
    static void initToolkit() {
        // 在所有测试之前初始化 JavaFX Toolkit，只需调用一次
        try {
            Platform.startup(() -> {
                // do nothing
            });
        } catch (IllegalStateException ignored) {
            // 如果 Toolkit 已经初始化，则会抛 IllegalStateException，忽略即可
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        // 直接 new 控制器实例，不调用 initialize()
        controller = new StartMenuController();

        // 通过反射依次给四个私有字段注入 Button 实例
        injectButton("createGameButton");
        injectButton("joinRoomButton");
        injectButton("helpButton");
        injectButton("exitButton");
    }

    /**
     * 辅助：通过反射给 controller 中的某个私有 Button 字段赋值一个新的 Button()
     */
    private void injectButton(String fieldName) throws Exception {
        Field field = StartMenuController.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(controller, new Button());
    }

    @Test
    void testMouseEnteredAndExitedForCreateGameButton() {
        Button btn = getButton("createGameButton");

        // 初始 opacity 应为 1.0
        assertEquals(1.0, btn.getOpacity(), 0.0001);

        // mouseEnteredCreate 设置为 0.5
        assertDoesNotThrow(() -> controller.mouseEnteredCreate((MouseEvent) null));
        assertEquals(0.5, btn.getOpacity(), 0.0001);

        // mouseExitedCreate 恢复为 1.0
        assertDoesNotThrow(() -> controller.mouseExitedCreate((MouseEvent) null));
        assertEquals(1.0, btn.getOpacity(), 0.0001);
    }

    @Test
    void testMouseEnteredAndExitedForJoinRoomButton() {
        Button btn = getButton("joinRoomButton");

        assertEquals(1.0, btn.getOpacity(), 0.0001);

        assertDoesNotThrow(() -> controller.mouseEnteredJoin((MouseEvent) null));
        assertEquals(0.5, btn.getOpacity(), 0.0001);

        assertDoesNotThrow(() -> controller.mouseExitedJoin((MouseEvent) null));
        assertEquals(1.0, btn.getOpacity(), 0.0001);
    }

    @Test
    void testMouseEnteredAndExitedForHelpButton() {
        Button btn = getButton("helpButton");

        assertEquals(1.0, btn.getOpacity(), 0.0001);

        assertDoesNotThrow(() -> controller.mouseEnteredHelp((MouseEvent) null));
        assertEquals(0.5, btn.getOpacity(), 0.0001);

        assertDoesNotThrow(() -> controller.mouseExitedHelp((MouseEvent) null));
        assertEquals(1.0, btn.getOpacity(), 0.0001);
    }

    @Test
    void testMouseEnteredAndExitedForExitButton() {
        Button btn = getButton("exitButton");

        assertEquals(1.0, btn.getOpacity(), 0.0001);

        assertDoesNotThrow(() -> controller.mouseEnteredExit((MouseEvent) null));
        assertEquals(0.5, btn.getOpacity(), 0.0001);

        assertDoesNotThrow(() -> controller.mouseExitedExit((MouseEvent) null));
        assertEquals(1.0, btn.getOpacity(), 0.0001);
    }

    /**
     * 通过反射读取 controller 中对应名称的 Button 私有字段
     */
    private Button getButton(String fieldName) {
        try {
            Field field = StartMenuController.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (Button) field.get(controller);
        } catch (Exception e) {
            throw new RuntimeException("反射获取 Button 字段失败: " + fieldName, e);
        }
    }
}
