package edu.bdic.forbiddenisland.model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TreasureDeckTest {

    @Test
    void testInitialDeckCompositionAndSize() {
        TreasureDeck deck = new TreasureDeck();

        // Draw all cards into a list
        List<TreasureCard> drawn = new ArrayList<>();
        TreasureCard card;
        while ((card = deck.draw()) != null) {
            drawn.add(card);
        }

        // Total should be 28: 5×(EARTH, FIRE, OCEAN, WIND) + 3×(HELICOPTER, WATERRISE) + 2×(SANDBAG)
        assertEquals(28, drawn.size(), "初始牌堆应有 28 张卡");

        // Count occurrences by type
        Map<TreasureCardType, Integer> counts = new EnumMap<>(TreasureCardType.class);
        for (TreasureCardType t : TreasureCardType.values()) {
            counts.put(t, 0);
        }
        for (TreasureCard tc : drawn) {
            counts.put(tc.getType(), counts.get(tc.getType()) + 1);
        }

        assertEquals(5, counts.get(TreasureCardType.EARTH), "应有 5 张 EARTH 卡");
        assertEquals(5, counts.get(TreasureCardType.FIRE), "应有 5 张 FIRE 卡");
        assertEquals(5, counts.get(TreasureCardType.OCEAN), "应有 5 张 OCEAN 卡");
        assertEquals(5, counts.get(TreasureCardType.WIND), "应有 5 张 WIND 卡");
        assertEquals(3, counts.get(TreasureCardType.HELICOPTER), "应有 3 张 HELICOPTER 卡");
        assertEquals(3, counts.get(TreasureCardType.WATERRISE), "应有 3 张 WATERRISE 卡");
        assertEquals(2, counts.get(TreasureCardType.SANDBAG), "应有 2 张 SANDBAG 卡");

        // After drawing all, deck should be empty and draw() returns null
        assertTrue(deck.isEmpty(), "抽完所有卡后，isEmpty 应为 true");
        assertNull(deck.draw(), "所有卡均被抽走后 draw 应返回 null");
    }

    @Test
    void testDrawWithoutDiscardReturnsNullOnceEmpty() {
        TreasureDeck deck = new TreasureDeck();
        // Draw all without discarding
        for (int i = 0; i < 28; i++) {
            assertNotNull(deck.draw(), "前28次 draw 应返回卡");
        }
        // Now empty
        assertTrue(deck.isEmpty(), "抽完所有卡后，isEmpty 应为 true");
        assertNull(deck.draw(), "抽牌堆和弃牌堆都空时 draw 应返回 null");
    }

    @Test
    void testDiscardAndRecycleBehavior() {
        TreasureDeck deck = new TreasureDeck();

        // Draw one card and immediately discard it
        TreasureCard first = deck.draw();
        assertNotNull(first, "draw 出来的第一张卡不应为 null");
        deck.discard(first);

        // Now draw remaining 27 cards into a list (discard pile only has 'first')
        List<TreasureCard> remaining = new ArrayList<>();
        TreasureCard c;
        for (int i = 0; i < 27; i++) {
            c = deck.draw();
            assertNotNull(c, "前27次 draw 应返回卡");
            remaining.add(c);
        }
        assertTrue(deck.isEmpty(), "此时抽牌堆应为空");

        // Discard pile has exactly the one card we discarded earlier
        // Now next draw should recycle that one card and return it (shuffle doesn't matter for single card)
        TreasureCard recycled = deck.draw();
        assertNotNull(recycled, "recycle 后 draw 应返回之前弃置的卡");
        assertEquals(first.getType(), recycled.getType(), "recycle 后应返回最初弃置的卡");

        // After that, deck is empty again
        assertTrue(deck.isEmpty(), "再次 draw 后抽牌堆应为空");
        assertNull(deck.draw(), "所有牌均被抽走后 draw 再次应返回 null");
    }

    @Test
    void testMultipleDiscardsAndRecycleKeepsAllCards() {
        TreasureDeck deck = new TreasureDeck();
        List<TreasureCard> initialDrawn = new ArrayList<>();

        // Draw and discard first two cards
        TreasureCard c1 = deck.draw();
        TreasureCard c2 = deck.draw();
        assertNotNull(c1);
        assertNotNull(c2);
        deck.discard(c1);
        deck.discard(c2);

        // Draw remaining 26 cards
        while (!deck.isEmpty()) {
            initialDrawn.add(deck.draw());
        }
        assertTrue(deck.isEmpty(), "取完所有卡后抽牌堆应为空");

        // Discard pile has at least c1 and c2
        // Now recycle and collect all cards again
        deck.recycleDiscards();
        List<TreasureCardType> recycledTypes = new ArrayList<>();
        TreasureCard rc;
        while ((rc = deck.draw()) != null) {
            recycledTypes.add(rc.getType());
        }
        // Recycled types should contain c1 and c2 types exactly once each
        long countC1 = recycledTypes.stream().filter(t -> t == c1.getType()).count();
        long countC2 = recycledTypes.stream().filter(t -> t == c2.getType()).count();
        assertEquals(1, countC1, "recycle 后应包含之前弃置的 c1 类型一次");
        assertEquals(1, countC2, "recycle 后应包含之前弃置的 c2 类型一次");

        // Total size after recycle draw should be 2 (the two discarded)
        assertEquals(2, recycledTypes.size(), "recycle 后应能抽到两张卡");
        assertTrue(deck.isEmpty(), "再抽完后应为空");
    }

    @Test
    void testIsEmptyReflectsDrawPileStatus() {
        TreasureDeck deck = new TreasureDeck();
        assertFalse(deck.isEmpty(), "新创建的 Deck 不应为空");

        // Draw all cards
        for (int i = 0; i < 28; i++) {
            deck.draw();
        }
        assertTrue(deck.isEmpty(), "取完所有卡后 isEmpty 应为 true");

        // Discard a card manually (drawPile empty)
        deck.discard(new TreasureCard(TreasureCardType.SANDBAG));
        assertTrue(deck.isEmpty(), "drawPile 空时即便有弃牌，isEmpty 应为 true");

        // Recycle discards → drawPile non-empty
        deck.recycleDiscards();
        assertFalse(deck.isEmpty(), "recycle 后 drawPile 不应为空");

        // Draw recycled card → deck empty again
        deck.draw();
        assertTrue(deck.isEmpty(), "抽完 recycle 卡后 isEmpty 应为 true");
    }
}
