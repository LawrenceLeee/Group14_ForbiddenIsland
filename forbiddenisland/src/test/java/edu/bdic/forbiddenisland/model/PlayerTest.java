package edu.bdic.forbiddenisland.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerTest {

    private static class DummyTreasureCard extends TreasureCard {
        DummyTreasureCard() {
            super(null); // Assuming a constructor exists; adjust if necessary
        }
    }

    private Player player;

    @BeforeEach
    void setUp() {
        // Use the constructor with tileIndex
        player = new Player(3, Profession.EXPLORER, 10);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(3, player.getPlayerIndex(), "playerIndex 应与构造时传入一致");
        assertEquals(Profession.EXPLORER, player.getProfession(), "profession 应与构造时传入一致");
        assertEquals(10, player.getTileIndex(), "tileIndex 应与构造时传入一致");
    }

    @Test
    void testDefaultTileIndexConstructor() {
        // 使用不带 tileIndex 的构造器，tileIndex 应默认为 0
        Player p2 = new Player(5, Profession.PILOT);
        assertEquals(5, p2.getPlayerIndex());
        assertEquals(Profession.PILOT, p2.getProfession());
        assertEquals(0, p2.getTileIndex(), "不带 tileIndex 构造器 tileIndex 应为 0");
    }

    @Test
    void testSetTileIndexAndMoveTo() {
        player.setTileIndex(15);
        assertEquals(15, player.getTileIndex(), "setTileIndex 应正确修改 tileIndex");

        player.moveTo(22);
        assertEquals(22, player.getTileIndex(), "moveTo 应正确修改 tileIndex");
    }

    @Test
    void testHandCardsOperations() {
        Queue<TreasureCard> hand = player.getHandCards();
        assertNotNull(hand, "getHandCards 不应返回 null");
        assertTrue(hand.isEmpty(), "初始手牌应为空");
        assertEquals(0, player.handSize(), "初始 handSize 应为 0");

        // 使用 Mockito 创建两个 TreasureCard 对象
        TreasureCard card1 = mock(TreasureCard.class);
        TreasureCard card2 = mock(TreasureCard.class);

        player.addCard(card1);
        assertEquals(1, player.handSize(), "添加一张卡后 handSize 应为 1");
        assertTrue(hand.contains(card1), "handCards 应包含刚添加的 card1");

        player.addCard(card2);
        assertEquals(2, player.handSize(), "再添加一张卡后 handSize 应为 2");
        assertTrue(hand.contains(card2), "handCards 应包含刚添加的 card2");

        player.removeCard(card1);
        assertEquals(1, player.handSize(), "移除 card1 后 handSize 应为 1");
        assertFalse(hand.contains(card1), "handCards 不应再包含 card1");

        player.removeCard(card2);
        assertEquals(0, player.handSize(), "移除 card2 后 handSize 应为 0");
        assertTrue(hand.isEmpty(), "handCards 应为空");
    }

    @Test
    void testRemoveNonexistentCardDoesNothing() {
        TreasureCard card = mock(TreasureCard.class);
        player.removeCard(card);
        assertEquals(0, player.handSize(), "移除不存在的卡时 handSize 不应改变");
    }

    @Test
    void testEqualityAndHashCodeBehavior() {
        // 确保不同 playerIndex 的 Player 不相等
        Player sameFields = new Player(3, Profession.EXPLORER, 10);
        assertNotSame(player, sameFields, "不同实例即使字段相同也不是同一对象");
        assertEquals(player.getPlayerIndex(), sameFields.getPlayerIndex());
        assertEquals(player.getProfession(), sameFields.getProfession());
        assertEquals(player.getTileIndex(), sameFields.getTileIndex());
    }
}
