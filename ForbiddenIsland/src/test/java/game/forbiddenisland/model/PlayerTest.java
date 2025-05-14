package game.forbiddenisland.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import team.group14.forbiddenisland.model.adventurer.Engineer;
import team.group14.forbiddenisland.model.adventurer.Pilot;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Player target;
    private IslandTile tile1;
    private IslandTile tile2;
    private TreasureCard fireCard;

    @BeforeEach
    void setUp() {
        player = new Player("Alice", new Engineer());
        target = new Player("Bob", new Pilot());
        tile1 = new IslandTile(2);  // 与 FIRE 宝藏关联
        tile2 = new IslandTile(5);  // 无宝藏关联
        fireCard = new TreasureCard(Treasure.FIRE);
    }

    @Test
    void testMoveUpdatesCurrentTile() {
        player.move(tile1);
        assertEquals(tile1, player.getCurrentTile(), "Player should move to the target tile");
    }

    @Test
    void testShoreUpFloodedTile() {
        tile1.flood(); // NORMAL -> FLOODED
        assertEquals(TileState.FLOODED, tile1.getState());

        player.shoreUp(tile1);
        assertEquals(TileState.NORMAL, tile1.getState(), "Tile should be restored after shoring up");
    }

    @Test
    void testAddCardToHand() {
        assertEquals(0, player.getHand().size());
        player.addCard(fireCard);
        assertEquals(1, player.getHand().size());
        assertTrue(player.getHand().contains(fireCard), "Card should be added to hand");
    }

    @Test
    void testGiveCardToOtherPlayer() {
        player.addCard(fireCard);
        assertEquals(1, player.getHand().size());
        assertEquals(0, target.getHand().size());

        player.giveCard(target, fireCard);

        assertEquals(0, player.getHand().size(), "Player should have given away the card");
        assertEquals(1, target.getHand().size(), "Target should have received the card");
        assertTrue(target.getHand().contains(fireCard));
    }

    @Test
    void testCaptureTreasureFailsByDefault() {
        // 默认实现中，captureTreasure 一律返回 false
        player.addCard(new TreasureCard(Treasure.FIRE));
        player.addCard(new TreasureCard(Treasure.FIRE));
        player.addCard(new TreasureCard(Treasure.FIRE));
        player.addCard(new TreasureCard(Treasure.FIRE));
        player.move(tile1); // FIRE 相关岛屿
        assertFalse(player.captureTreasure(Treasure.FIRE),
                "captureTreasure should return false as it's not implemented yet");
    }

    @Test
    void testAdventurerSpecialMove() {
        // 使用 Pilot 的特殊移动能力
        IslandTile remoteTile = new IslandTile(19);
        target.getAdventurer().specialMove(target, remoteTile);
        assertEquals(remoteTile, target.getCurrentTile(),
                "Pilot should be able to specialMove to any tile");
    }
}
