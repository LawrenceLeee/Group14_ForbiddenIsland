package edu.bdic.forbiddenisland.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testGetTileIndex() {
        Card card = new Card(42);
        assertEquals(42, card.getTileIndex(), "getTileIndex 应返回构造时的值");
    }

    @Test
    void testToStringFormat() {
        Card card = new Card(7);
        String str = card.toString();
        assertTrue(str.contains("Card"), "toString 应包含类名 Card");
        assertTrue(str.contains("tileIndex=7"), "toString 应包含 tileIndex=7");
        assertTrue(str.startsWith("Card{") && str.endsWith("}"), "toString 格式应为 Card{tileIndex=7}");
    }

    @Test
    void testEqualsAndHashCode_sameTileIndex() {
        Card c1 = new Card(5);
        Card c2 = new Card(5);

        assertEquals(c1, c2, "两个 tileIndex 相同的 Card 应相等");
        assertEquals(c1.hashCode(), c2.hashCode(), "相等的对象应有相同的 hashCode");
    }

    @Test
    void testEquals_differentTileIndex() {
        Card c1 = new Card(5);
        Card c2 = new Card(6);

        assertNotEquals(c1, c2, "tileIndex 不同的 Card 应不相等");
        assertNotEquals(c1.hashCode(), c2.hashCode(), "不同的对象 hashCode 应不同");
    }

    @Test
    void testEquals_nullAndDifferentClass() {
        Card card = new Card(3);

        assertNotEquals(card, null, "Card 不应与 null 相等");
        assertNotEquals(card, "not a card", "Card 不应与不同类型对象相等");
    }

    @Test
    void testEquals_sameInstance() {
        Card card = new Card(10);
        assertEquals(card, card, "同一实例应与自身相等");
    }
}
