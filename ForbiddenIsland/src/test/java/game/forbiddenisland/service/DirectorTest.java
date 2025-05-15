package game.forbiddenisland.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import team.group14.forbiddenisland.service.Director;
import static org.junit.jupiter.api.Assertions.*;

public class DirectorTest {
    private Director director;

    @BeforeEach
    void setUp() {
        director = Director.getInstance();
        director.initTestGame();
    }

    @Test
    void testInitGame() {
        director.setGameLevel(1);
        director.initGame();

        assertNotNull(director.getGameState());
        assertNotNull(director.getWaterMeter());
        assertNotNull(director.getTreasureDeck());
        assertNotNull(director.getFloodDeck());
        assertEquals(2, director.getPlayers().size());
        assertEquals(24, director.getIslandTiles().size());
    }

    @Test
    void testInitTreasureDeck() {
        director.setGameLevel(1);
        director.initGame();
        Deck<TreasureCard> treasureDeck = director.getTreasureDeck();
        assertNotNull(treasureDeck);
        assertEquals(24, treasureDeck.size());
    }

    @Test
    void testInitFloodDeck() {
        director.setGameLevel(1);
        director.initGame();
        Deck<FloodCard> floodDeck = director.getFloodDeck();
        assertNotNull(floodDeck);
        assertEquals(18, floodDeck.size());
    }

    @Test
    void testGameOver() {
        director.setGameLevel(1);
        director.initGame();
        director.getGameState().endGame(false);
        // 调用 nextTurn 时应自动触发 gameOver()，测试 gameOver 不抛异常即可
        assertDoesNotThrow(() -> director.nextTurn());
        assertTrue(director.getGameState().isGameOver());
    }
}
