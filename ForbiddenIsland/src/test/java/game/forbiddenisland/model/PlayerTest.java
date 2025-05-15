package game.forbiddenisland.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import team.group14.forbiddenisland.model.adventurer.Diver;
import team.group14.forbiddenisland.model.adventurer.Pilot;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player1;
    private Player player2;
    private IslandTile tile;
    private TreasureCard treasureCard1;
    private TreasureCard treasureCard2;

    @BeforeEach
    void setUp(){
        player1 = new Player("TestPlayer1", new Pilot());
        player2 = new Player("TestPlayer2", new Diver());
        treasureCard1 = new TreasureCard(Treasure.FIRE);
        treasureCard2 = new TreasureCard(Treasure.FIRE);
        tile = new IslandTile(2);
        player1.setCurrentTile(tile);
    }

    @Test
    void testAddCard() {
        player1.addCard(treasureCard1);
        assertTrue(player1.getHand().contains(treasureCard1));
    }

    @Test
    void testGiveCard() {
        player1.addCard(treasureCard1);
        player1.giveCard(player2, treasureCard1);
        assertFalse(player1.getHand().contains(treasureCard1));
        assertTrue(player2.getHand().contains(treasureCard1));
    }

    @Test
    void testMove() {
        IslandTile destination = new IslandTile(3);
        player1.move(destination);
        assertEquals(destination, player1.getCurrentTile());
    }

    @Test
    void testShoreUp() {
        tile.flood();
        assertEquals(TileState.FLOODED, tile.getState());
        player1.shoreUp(tile);
        assertEquals(TileState.NORMAL, tile.getState());
    }

    @Test
    void testCaptureTreasureFail() {
        player1.addCard(treasureCard1);
        assertFalse(player1.captureTreasure(Treasure.FIRE));
    }

    @Test
    void testCaptureTreasureSuccess() {
        for (int i = 0; i < 4; i++) {
            player1.addCard(new TreasureCard(Treasure.FIRE));
        }
        player1.setCurrentTile(new IslandTile(2));
        assertTrue(player1.captureTreasure(Treasure.FIRE));
    }
}