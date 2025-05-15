package game.forbiddenisland.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import team.group14.forbiddenisland.model.adventurer.*;
import team.group14.forbiddenisland.service.Director;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {
    private GameState gameState;

    @BeforeEach
    void setUp() {
        Director.getInstance().initTestGame();
        gameState = new GameState();
    }

    @Test
    void testInitialState() {
        // 测试初始状态
        assertEquals(1, gameState.getTurnNumber());
        assertEquals(0, gameState.getCurrentPlayerIndex());
        assertEquals("ACTION", gameState.getPhase());
        assertFalse(gameState.isGameOver());
        assertFalse(gameState.isVictory());
        assertEquals(3, gameState.getActionsRemaining());
        assertEquals(1, gameState.getWaterLevel());
    }

    @Test
    void testNextTurn() {
        Player currentPlayer = gameState.getCurrentPlayer();
        gameState.nextTurn();
        assertEquals(2, gameState.getTurnNumber());
        assertNotEquals(currentPlayer, gameState.getCurrentPlayer());
        assertEquals("ACTION", gameState.getPhase());
        assertEquals(3, gameState.getActionsRemaining());
    }

    @Test
    void testEndGameVictory() {
        gameState.endGame(true);
        assertTrue(gameState.isGameOver());
        assertTrue(gameState.isVictory());
    }

    @Test
    void testEndGameDefeat() {
        gameState.endGame(false);
        assertTrue(gameState.isGameOver());
        assertFalse(gameState.isVictory());
    }

    @Test
    void testCollectTreasure() {
        gameState.collectTreasure(Treasure.FIRE);
        gameState.collectTreasure(Treasure.FIRE);//重复
        gameState.collectTreasure(Treasure.OCEAN);
        List<Treasure> collectedTreasures = gameState.getCollectedTreasures();
        assertEquals(2, collectedTreasures.size());
        assertTrue(collectedTreasures.contains(Treasure.FIRE));
        assertTrue(collectedTreasures.contains(Treasure.OCEAN));
    }

    @Test
    void testIncrementWaterLevel() {
        int oldWaterLevel = gameState.getWaterLevel();
        gameState.increaseWaterLevel();
        assertEquals(oldWaterLevel + 1, gameState.getWaterLevel());
    }

    @Test
    void testUseAction() {
        gameState.useAction();
        assertEquals(2, gameState.getActionsRemaining());
        gameState.useAction();
        assertEquals(1, gameState.getActionsRemaining());
        gameState.useAction();
        assertEquals(0, gameState.getActionsRemaining());
        gameState.useAction();
        assertEquals(0, gameState.getActionsRemaining());//超过不减.不执行行动
    }

    @Test
    void testNextPhase() {
        gameState.nextPhase();
        assertEquals("DRAW_TREASURE", gameState.getPhase());
        gameState.nextPhase();
        assertEquals("DRAW_FLOOD", gameState.getPhase());
        gameState.nextPhase();
        assertEquals("ACTION", gameState.getPhase());
    }
}
