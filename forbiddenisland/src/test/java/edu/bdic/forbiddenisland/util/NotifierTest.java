package edu.bdic.forbiddenisland.util;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class NotifierTest {

    @BeforeAll
    static void initJfxToolkit() throws InterruptedException {
        // Initialize JavaFX Toolkit
        new JFXPanel();
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        assertTrue(latch.await(5, TimeUnit.SECONDS), "JavaFX toolkit should initialize within 5 seconds");
    }

    @Test
    void testNotify_withCustomColorAndWidth_doesNotThrow() throws InterruptedException {
        // Create and show a Stage on the FX thread, then call notify
        AtomicReference<Stage> ownerRef = new AtomicReference<>();
        CountDownLatch setupLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setWidth(200);
            stage.setHeight(200);
            stage.setX(0);
            stage.setY(0);
            stage.show(); // must show to serve as valid owner
            ownerRef.set(stage);
            setupLatch.countDown();
        });
        assertTrue(setupLatch.await(5, TimeUnit.SECONDS), "FX thread should have created and shown a Stage");

        // Now call notify on the FX thread, ensuring no exception
        CountDownLatch notifyLatch = new CountDownLatch(1);
        AtomicReference<Throwable> exRef = new AtomicReference<>();

        Platform.runLater(() -> {
            try {
                Notifier.notify(ownerRef.get(), "测试消息", "#336699", 300);
            } catch (Throwable t) {
                exRef.set(t);
            } finally {
                notifyLatch.countDown();
            }
        });
        assertTrue(notifyLatch.await(5, TimeUnit.SECONDS), "FX thread should have executed notify");
        assertNull(exRef.get(), "Calling notify should not throw");
    }

    @Test
    void testNotify_showsPopup() throws InterruptedException {
        // Create and show a Stage on the FX thread
        AtomicReference<Stage> ownerRef = new AtomicReference<>();
        CountDownLatch setupLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setWidth(200);
            stage.setHeight(200);
            stage.setX(0);
            stage.setY(0);
            stage.show(); // show it
            ownerRef.set(stage);
            setupLatch.countDown();
        });
        assertTrue(setupLatch.await(5, TimeUnit.SECONDS), "FX thread should have created and shown a Stage");

        // Record number of PopupWindow instances before notify
        AtomicInteger beforeCount = new AtomicInteger();
        CountDownLatch beforeLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            beforeCount.set(PopupWindow.getWindows().size());
            beforeLatch.countDown();
        });
        assertTrue(beforeLatch.await(5, TimeUnit.SECONDS), "FX thread should have read initial window count");

        // Call notify on the FX thread
        CountDownLatch notifyLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            Notifier.notify(ownerRef.get(), "弹出测试");
            notifyLatch.countDown();
        });
        assertTrue(notifyLatch.await(5, TimeUnit.SECONDS), "FX thread should have executed notify");

        // Wait enough time for the Popup to show (500ms animation)
        Thread.sleep(600);

        // Record number of PopupWindow instances after notify
        AtomicInteger afterCount = new AtomicInteger();
        CountDownLatch afterLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            afterCount.set(PopupWindow.getWindows().size());
            afterLatch.countDown();
        });
        assertTrue(afterLatch.await(5, TimeUnit.SECONDS), "FX thread should have read new window count");

        assertEquals(beforeCount.get() + 1, afterCount.get(),
                "Calling notify should add exactly one PopupWindow");
    }
}
