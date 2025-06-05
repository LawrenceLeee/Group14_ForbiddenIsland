package edu.bdic.forbiddenisland.view;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * - 先通过 Platform.startup(...) 初始化 JavaFX Toolkit。
 * - 在 Platform.runLater(...) 中创建 Stage 并调用 view.init(stage)，
 *   并用 CountDownLatch 等待执行完成。
 * - 如果 view.init(...) 在 JavaFX 应用线程中抛出了任何异常，都会记录到 errorRef 中。
 * - assertDoesNotThrow 不再适合，因为我们已经把调用放在 FX 线程；改为在主线程等待 latch，
 *   然后 assert errorRef.get() 为 null，表示 init 执行过程中没抛异常。
 */
public class StartMenuViewTest {

    @BeforeAll
    static void initToolkit() {
        // 初始化 JavaFX Toolkit（只需做一次）
        try {
            Platform.startup(() -> {
                // no-op
            });
        } catch (IllegalStateException ignored) {
            // 如果已经启动，抛 IllegalStateException，则忽略即可
        }
    }

    @Test
    void testInit_doesNotThrowAnyException() throws Exception {
        StartMenuView view = new StartMenuView();

        // 用来捕获 view.init(...) 执行时抛出的异常
        AtomicReference<Throwable> errorRef = new AtomicReference<>(null);
        // 用来等待 FX 线程执行完成
        CountDownLatch latch = new CountDownLatch(1);

        // 临时屏蔽 System.err，避免内部打印的 LoadException/IOException 堆栈干扰测试
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream()));

        // 在 FX 应用线程中执行
        Platform.runLater(() -> {
            try {
                // 在 FX 线程中创建 Stage 并调用 init
                Stage stage = new Stage();
                view.init(stage);
            } catch (Throwable t) {
                // 捕获任何异常
                errorRef.set(t);
            } finally {
                // 无论成功或异常，都要 countdown
                latch.countDown();
            }
        });

        // 等待 FX 线程执行，超时 5 秒
        boolean completed = latch.await(5, TimeUnit.SECONDS);
        // 恢复 System.err
        System.setErr(originalErr);

        // 确认 FX 线程在 5 秒内执行完毕
        assertTrue(completed, "FX 线程未在 5 秒内完成");

        // 如果 errorRef 非 null，则说明 init(...) 抛出了异常
        if (errorRef.get() != null) {
            // 让断言失败并显示异常堆栈
            fail("view.init(stage) 在 FX 线程中抛出了异常: "
                    + errorRef.get().getClass().getName() + " - "
                    + errorRef.get().getMessage());
        }
    }
}