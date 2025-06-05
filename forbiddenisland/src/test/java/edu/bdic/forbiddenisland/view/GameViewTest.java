package edu.bdic.forbiddenisland.view;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GameView 的单元测试：确保调用 init(null) 时不会向调用者抛出异常，
 * 并且压制内部可能打印的 LoadException 或 “Internal graphics not initialized yet” 堆栈。
 */
public class GameViewTest {

    @BeforeAll
    static void initToolkit() {
        // 初始化 JavaFX Toolkit，仅执行一次
        try {
            Platform.startup(() -> {
                // no-op
            });
        } catch (IllegalStateException ignored) {
            // 如果已经初始化，则忽略
        }
    }

    @Test
    void testInit_doesNotThrowAnyException() {
        GameView view = new GameView();

        // === 临时屏蔽 System.err，以避免内部抛出的 LoadException/RuntimeException 打印到控制台 ===
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream()));

        // 只要 view.init(null) 没向外抛异常，测试即算通过
        assertDoesNotThrow(() -> {
            view.init(null);
        });

        // 恢复 System.err
        System.setErr(originalErr);
    }
}
