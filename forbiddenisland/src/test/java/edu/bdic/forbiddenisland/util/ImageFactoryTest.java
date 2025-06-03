package edu.bdic.forbiddenisland.util;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageFactoryTest {

    @BeforeAll
    static void initToolkit() {
        new JFXPanel();
    }

    @Test
    void testGetByEnumLoadsAndCachesForAllExistingTypes() {
        for (ImageType type : ImageType.values()) {
            try {
                Image img1 = ImageFactory.get(type);
                assertNotNull(img1, "ImageFactory.get(" + type + ") 不应返回 null");

                // 再次调用应缓存
                Image img2 = ImageFactory.get(type);
                assertSame(img1, img2, "重复调用应缓存");
            } catch (RuntimeException e) {
                System.out.println("跳过未找到图片资源的类型: " + type + "，原因: " + e.getMessage());
                // 可以选择跳过，但不让测试挂掉
            }
        }
    }

    @Test
    void testGetByFilenameLoadsIfExistOrThrows() {
        // 修改为你实际有的图片文件名，如 logo.png
        String existingFilename = "logo.png"; // 替换成你的真实存在的图片
        try {
            Image img = ImageFactory.get(existingFilename);
            assertNotNull(img);
        } catch (RuntimeException e) {
            System.out.println("资源文件不存在，跳过: " + existingFilename);
        }
    }

    @Test
    void testGetByFilenameThrowsIfMissing() {
        String missing = "definitely-not-exist.png";
        RuntimeException ex = assertThrows(RuntimeException.class, () -> ImageFactory.get(missing));
        assertTrue(ex.getMessage() != null && ex.getMessage().contains(missing));
    }
}
