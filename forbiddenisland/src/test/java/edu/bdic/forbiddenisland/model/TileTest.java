package edu.bdic.forbiddenisland.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void testConstructorInitializesCoordinatesAndDefaultStatus() {
        Tile tile = new Tile(2, 5);

        assertEquals(2, tile.getX(), "X 坐标应与构造时传入一致");
        assertEquals(5, tile.getY(), "Y 坐标应与构造时传入一致");
        assertEquals(TileStatus.NORMAL, tile.getStatus(), "默认状态应为 NORMAL");
    }

    @Test
    void testSetStatusAndGetStatus() {
        Tile tile = new Tile(0, 0);

        tile.setStatus(TileStatus.FLOODED);
        assertEquals(TileStatus.FLOODED, tile.getStatus(), "状态应被设置为 FLOODED");

        tile.setStatus(TileStatus.SUNK);
        assertEquals(TileStatus.SUNK, tile.getStatus(), "状态应被设置为 SUNK");
    }
}
