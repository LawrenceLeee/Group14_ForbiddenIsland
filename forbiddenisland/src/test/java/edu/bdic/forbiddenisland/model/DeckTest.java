package edu.bdic.forbiddenisland.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void testInitialDrawAndEmpty() {
        List<Integer> cards = Arrays.asList(1, 2, 3);
        Deck<Integer> deck = new Deck<>(cards);

        // Initially not empty
        assertFalse(deck.isEmpty(), "新创建的抽牌堆不应为空");

        // Draw in order
        assertEquals(1, deck.draw(), "第一次 draw 应返回 1");
        assertEquals(2, deck.draw(), "第二次 draw 应返回 2");
        assertEquals(3, deck.draw(), "第三次 draw 应返回 3");

        // Now drawPile is empty and discardPile is empty → draw returns null
        assertTrue(deck.isEmpty(), "取完所有卡后 drawPile 应为空");
        assertNull(deck.draw(), "drawPile 与 discardPile 都空时 draw 应返回 null");
    }

    @Test
    void testDiscardAndRecycle() {
        List<String> cards = Arrays.asList("A");
        Deck<String> deck = new Deck<>(cards);

        // Draw the only card and discard it
        String drawn = deck.draw();
        assertEquals("A", drawn, "draw 应返回 'A'");
        deck.discard(drawn);

        // drawPile empty, discardPile contains "A"
        assertTrue(deck.isEmpty(), "drawPile 已空，应返回 true");
        // Next draw should recycle discardPile and return "A"
        String recycled = deck.draw();
        assertEquals("A", recycled, "recycle 后 draw 应返回之前弃置的 'A'");

        // After drawing recycled card, both piles empty again
        assertTrue(deck.isEmpty(), "再次 draw 后抽牌堆应为空");
        assertNull(deck.draw(), "所有牌均被抽走后 draw 应返回 null");
    }

    @Test
    void testDrawWithoutDiscardsReturnsNull() {
        Deck<Integer> deck = new Deck<>(Arrays.asList());
        // Empty deck from the start
        assertTrue(deck.isEmpty(), "空列表创建的 Deck 应立即 isEmpty");
        assertNull(deck.draw(), "空 Deck draw 应返回 null");
    }

    @Test
    void testIsEmptyBehavior() {
        Deck<Integer> deck = new Deck<>(Arrays.asList(10, 20));
        // Initially false
        assertFalse(deck.isEmpty());
        // Draw both cards
        deck.draw();
        deck.draw();
        assertTrue(deck.isEmpty(), "取完所有卡后 isEmpty 应为 true");
        // Discard one card manually, but drawPile still empty; isEmpty still true
        deck.discard(30);
        assertTrue(deck.isEmpty(), "尚未 recycle 且 drawPile 空时 isEmpty 仍为 true");
        // Recycle and then isEmpty should be false until that single card is drawn
        deck.recycleDiscards();
        assertFalse(deck.isEmpty(), "recycle 后抽牌堆不应为空");
        // Draw recycled card
        deck.draw();
        assertTrue(deck.isEmpty(), "抽完 recycle 后的卡，isEmpty 应为 true");
    }

    @Test
    void testMultipleDiscardsAndRecycleKeepsAllCards() {
        Deck<Integer> deck = new Deck<>(Arrays.asList(1, 2));
        // Draw both and discard both
        Integer first = deck.draw();
        Integer second = deck.draw();
        assertEquals(1, first);
        assertEquals(2, second);
        deck.discard(first);
        deck.discard(second);

        // drawPile empty, discardPile holds [1,2]
        assertTrue(deck.isEmpty());

        // After recycle, drawPile should have 1 and 2 (in some order)
        deck.recycleDiscards();
        // drawPile not empty
        assertFalse(deck.isEmpty());

        // Draw both; as order may shuffle, just collect results
        Integer x = deck.draw();
        Integer y = deck.draw();
        List<Integer> drawn = Arrays.asList(x, y);
        assertTrue(drawn.containsAll(Arrays.asList(1, 2)) && drawn.size() == 2,
                "recycle 后应能抽到之前弃置的 1 和 2，顺序可变");

        assertTrue(deck.isEmpty(), "抽完所有 recycle 后的卡应为空");
    }
}
