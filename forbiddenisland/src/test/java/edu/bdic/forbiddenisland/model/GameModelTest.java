package edu.bdic.forbiddenisland.model;

import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.bdic.forbiddenisland.controller.commands.MoveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    private GameModel model;

    @BeforeEach
    void setUp() {
        // 获取单例并重置核心状态 via reflection where needed
        model = GameModel.getInstance();

        // Reset internal state to defaults
        // 1) Clear players
        clearPlayers();
        // 2) Reset water level
        setWaterLevel(2);
        // 3) Reset gameState
        setGameState(GameModel.GameState.ONGOING);
        // 4) Clear listeners
        clearListeners();
        // 5) Reset lastInitializedSeed to allow re-initialize
        setLastInitializedSeed(Long.MIN_VALUE);
        // 6) Reset islandLayout
        model.setIslandLayout(generateValidLayout());
    }

    private void clearPlayers() {
        try {
            var playersField = GameModel.class.getDeclaredField("players");
            playersField.setAccessible(true);
            ((Map<?, ?>) playersField.get(model)).clear();
            var handsField = GameModel.class.getDeclaredField("playerTreasureHands");
            handsField.setAccessible(true);
            ((Map<?, ?>) handsField.get(model)).clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setWaterLevel(int level) {
        try {
            var wlField = GameModel.class.getDeclaredField("waterLevel");
            wlField.setAccessible(true);
            wlField.setInt(model, level);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setGameState(GameModel.GameState state) {
        try {
            var gsField = GameModel.class.getDeclaredField("gameState");
            gsField.setAccessible(true);
            gsField.set(model, state);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearListeners() {
        try {
            var listenersField = GameModel.class.getDeclaredField("listeners");
            listenersField.setAccessible(true);
            ((List<?>) listenersField.get(model)).clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setLastInitializedSeed(long seed) {
        try {
            var seedField = GameModel.class.getDeclaredField("lastInitializedSeed");
            seedField.setAccessible(true);
            seedField.setLong(model, seed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> generateValidLayout() {
        // 24 distinct indices 0..23
        List<Integer> layout = new ArrayList<>();
        for (int i = 0; i < 24; i++) layout.add(i);
        return layout;
    }

    @Test
    void testSetIslandLayoutValidAndInvalid() {
        // Valid layout size 24
        List<Integer> valid = generateValidLayout();
        assertDoesNotThrow(() -> model.setIslandLayout(valid));
        assertEquals(valid, model.getIslandLayout());

        // Invalid size (e.g., 23)
        List<Integer> shortList = new ArrayList<>(valid.subList(0, 23));
        assertThrows(IllegalArgumentException.class, () -> model.setIslandLayout(shortList));

        // Null layout
        assertThrows(IllegalArgumentException.class, () -> model.setIslandLayout(null));
    }

    @Test
    void testAddAndGetPlayers() {
        // Initially no players
        assertTrue(model.getPlayers().isEmpty());

        // Add player 0 with PROFESSION EXPLORER
        model.addPlayer(0, Profession.EXPLORER);
        assertEquals(1, model.getPlayers().size());
        assertNotNull(model.getPlayer(0));
        assertEquals(0, model.getPlayer(0).getPlayerIndex());
        assertEquals(Profession.EXPLORER, model.getPlayer(0).getProfession());

        // Add another player
        model.addPlayer(1, Profession.DIVER);
        assertEquals(2, model.getPlayers().size());

        // Adding same index again should do nothing
        model.addPlayer(0, Profession.PILOT);
        assertEquals(2, model.getPlayers().size());
        assertEquals(Profession.EXPLORER, model.getPlayer(0).getProfession());
    }

    @Test
    void testInitializeGameIdempotenceAndBasicSetup() {
        // Add two players before initializing
        model.addPlayer(0, Profession.EXPLORER);
        model.addPlayer(1, Profession.DIVER);

        long seed = 12345L;
        model.initializeGame(seed);

        // After initialization:
        // waterLevel resets to 2
        assertEquals(2, model.getWaterLevel());

        // gameState is ONGOING
        assertEquals(GameModel.GameState.ONGOING, model.getGameState());

        // currentPlayer should be min key (0)
        assertEquals(0, model.getCurrentPlayer());

        // actionPoints for both players = 3
        assertEquals(3, model.getActionPoints(0));
        assertEquals(3, model.getActionPoints(1));

        // Tile states: Some tiles may be flooded. At least one floodDeck.draw() happened.
        // Confirm that tile states are among DRY/FLOODED/SUNK
        for (int i = 0; i < 24; i++) {
            GameModel.TileState state = model.getTileState(i);
            assertTrue(EnumSet.of(GameModel.TileState.DRY, GameModel.TileState.FLOODED, GameModel.TileState.SUNK).contains(state));
        }

        // Second call with same seed should do nothing (idempotent)
        // Change a tile to test no reset on same seed
        model.updateTileStatus(0, "SUNK");
        assertEquals(GameModel.TileState.SUNK, model.getTileState(0));
        model.initializeGame(seed); // same seed
        // Tile 0 should remain SUNK
        assertEquals(GameModel.TileState.SUNK, model.getTileState(0));
    }

    @Test
    void testUseActionPointAndMovePlayer() {
        model.addPlayer(0, Profession.NAVIGATOR);
        // Manually set action points
        setActionPoints(0, 2);

        // Move player with index 0 to tile 5
        model.movePlayer(0, 5);
        assertEquals(1, model.getActionPoints(0));

        // If no action points left, movePlayer should do nothing
        setActionPoints(0, 0);
        model.movePlayer(0, 10);
        assertEquals(0, model.getActionPoints(0));
    }

    private void setActionPoints(int pid, int points) {
        try {
            var apField = GameModel.class.getDeclaredField("actionPoints");
            apField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> apMap = (Map<Integer, Integer>) apField.get(model);
            apMap.put(pid, points);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testShoreUpTileConsumesActionPointAndChangesState() {
        model.addPlayer(0, Profession.NAVIGATOR);
        // Ensure currentPlayer = 0 and actionPoints = 3
        setCurrentPlayer(0);
        setActionPoints(0, 1);

        // Make tile 4 FLOODED manually
        model.updateTileStatus(4, "FLOODED");
        assertEquals(GameModel.TileState.FLOODED, model.getTileState(4));

        model.shoreUpTile(4);
        // After shoreUp, tile 4 should be DRY
        assertEquals(GameModel.TileState.DRY, model.getTileState(4));
        // actionPoints decremented to 0
        assertEquals(0, model.getActionPoints(0));

        // If no AP, next shoreUp does nothing
        model.updateTileStatus(5, "FLOODED");
        model.shoreUpTile(5);
        assertEquals(GameModel.TileState.FLOODED, model.getTileState(5));
    }

    private void setCurrentPlayer(int pid) {
        try {
            var cpField = GameModel.class.getDeclaredField("currentPlayer");
            cpField.setAccessible(true);
            cpField.setInt(model, pid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDiscardTreasureAndCaptureTreasure() {
        model.addPlayer(0, Profession.EXPLORER);
        setCurrentPlayer(0);
        setActionPoints(0, 4);

        // Place player on an EARTH treasure tile (index 18)
        try {
            var player = model.getPlayer(0);
            var tileField = player.getClass().getDeclaredField("tileIndex");
            tileField.setAccessible(true);
            tileField.setInt(player, 18);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Give player 4 EARTH cards
        try {
            var handsField = GameModel.class.getDeclaredField("playerTreasureHands");
            handsField.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<Integer, List<TreasureCardType>> hands = (Map<Integer, List<TreasureCardType>>) handsField.get(model);
            hands.put(0, new ArrayList<>(Collections.nCopies(4, TreasureCardType.EARTH)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // captureTreasure should succeed (returns true) and consume AP
        boolean success = model.captureTreasure(0);
        assertTrue(success);
        assertEquals(3, model.getActionPoints(0));

        // Further capture fails (no 4 cards)
        boolean fail = model.captureTreasure(0);
        assertFalse(fail);
    }

    @Test
    void testEvaluateGameStateVictoryCondition() {
        model.addPlayer(0, Profession.EXPLORER);
        setCurrentPlayer(0);
        setActionPoints(0, 1);

        // Mock treasureDiscard to contain all four treasures
        try {
            var tdField = GameModel.class.getDeclaredField("treasureDiscard");
            tdField.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<TreasureCardType> tdList = (List<TreasureCardType>) tdField.get(model);
            tdList.clear();
            tdList.addAll(Arrays.asList(TreasureCardType.FIRE, TreasureCardType.EARTH, TreasureCardType.OCEAN, TreasureCardType.WIND));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Use helicopter lift
        model.useHelicopterLift();

        // Place player on Fool's Landing (index 3)
        try {
            var player = model.getPlayer(0);
            var tileField = player.getClass().getDeclaredField("tileIndex");
            tileField.setAccessible(true);
            tileField.setInt(player, 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Ensure tile 3 is not SUNK
        model.updateTileStatus(3, "DRY");

        // Evaluate
        GameModel.GameState state = model.evaluateGameState();
        assertEquals(GameModel.GameState.WON, state);
    }

    @Test
    void testEvaluateGameStateLossFloodedTreasureTiles() {
        model.addPlayer(0, Profession.EXPLORER);
        // Ensure no treasureDiscard entries, so a treasure remains uncollected
        // Sink both tiles for FIRE (indices 2 and 3)
        model.updateTileStatus(2, "SUNK");
        model.updateTileStatus(3, "SUNK");

        GameModel.GameState state = model.evaluateGameState();
        assertEquals(GameModel.GameState.LOST, state);
    }

    @Test
    void testListenersReceiveOnModelChanged() {
        AtomicBoolean called = new AtomicBoolean(false);
        // 这里不能用 called::set，改为 lambda 来符合 ModelChangeListener 接口
        ModelChangeListener listener = () -> called.set(true);
        model.addListener(listener);

        // Trigger a change: updateTileStatus
        model.updateTileStatus(0, "FLOODED");
        assertTrue(called.get());

        // Remove and ensure no further calls
        called.set(false);
        model.removeListener(listener);
        model.updateTileStatus(1, "FLOODED");
        assertFalse(called.get());
    }
}
