package edu.bdic.forbiddenisland.model;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureCardTest {

    @Test
    void testGetTypeReturnsCorrectEnum() {
        TreasureCard card = new TreasureCard(TreasureCardType.FIRE);
        assertEquals(TreasureCardType.FIRE, card.getType(), "getType 应返回构造时的 TreasureCardType");
    }

    @Test
    void testToStringReturnsTypeName() {
        TreasureCard card = new TreasureCard(TreasureCardType.WIND);
        assertEquals("WIND", card.toString(), "toString 应返回枚举名称");
    }

    @Test
    void testGetFxImageThrowsWhenResourceMissing() {
        // 这里使用一个在 classpath 中很可能不存在的文件名来确保 getResourceAsStream 返回 null
        // 使用现有枚举值；项目中通常没有对应路径下的图片
        TreasureCard card = new TreasureCard(TreasureCardType.EARTH);

        // 新建 Image 时传入的 InputStream 应为 null → 通常会抛 NullPointerException
        assertThrows(NullPointerException.class, () -> {
            Image img = card.getFxImage();
        });
    }
}
