package game.forbiddenisland.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import static org.junit.jupiter.api.Assertions.*;

public class IslandTileTest {
    IslandTile islandTile;

    @BeforeEach
    void setUp() {
        islandTile = new IslandTile(2); // 对应FIRE宝藏
    }

    @Test
    void testInitialState() {
        assertEquals(TileState.NORMAL, islandTile.getState());
        assertEquals(Treasure.FIRE, islandTile.getAssociatedTreasure());
    }

    @Test
    void testFlood() {
        islandTile.flood();
        assertEquals(TileState.FLOODED, islandTile.getState());
    }

    @Test
    void testSunk() {
        islandTile.flood();
        islandTile.flood();
        assertEquals(TileState.SUNK, islandTile.getState());
    }

    @Test
    void testShoreUp() {
        islandTile.flood();
        islandTile.shoreUp();
        assertEquals(TileState.NORMAL, islandTile.getState());
    }

    @Test
    void testShoreUpAlreadyNormal() {
        islandTile.shoreUp();
        assertEquals(TileState.NORMAL, islandTile.getState());
    }
}
