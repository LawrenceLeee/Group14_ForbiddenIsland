package game.forbiddenisland.model.adventurer;

import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.IslandTile;
import team.group14.forbiddenisland.model.Player;
import team.group14.forbiddenisland.model.adventurer.Pilot;

import static org.junit.jupiter.api.Assertions.*;

public class PilotTest {
    @Test
    void testRoleNameAndAbility() {
        Pilot pilot = new Pilot();
        assertEquals("Pilot", pilot.getRoleName());
        assertTrue(pilot.getSpecialAbilityDescription().contains("fly"));
    }

    @Test
    void testSpecialMove() {
        // Create a mock player and destination tile
        Pilot pilot = new Pilot();
        Player player = new Player("TestPlayer", pilot);
        IslandTile destination = new IslandTile(5);

        // Call the special move method
        pilot.specialMove(player, destination);

        // Verify that the player's position has been updated correctly
        assertEquals(destination, player.getCurrentTile());
    }
}
