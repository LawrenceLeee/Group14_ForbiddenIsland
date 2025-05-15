package game.forbiddenisland.model.adventurer;

import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.IslandTile;
import team.group14.forbiddenisland.model.Player;
import team.group14.forbiddenisland.model.adventurer.Engineer;

import static org.junit.jupiter.api.Assertions.*;

public class EngineerTest {
    @Test
    void testRoleNameAndAbility() {
        Engineer engineer = new Engineer();
        assertEquals("Engineer", engineer.getRoleName());
        assertTrue(engineer.getSpecialAbilityDescription().contains("fly"));
    }

    @Test
    void testSpecialMove() {
        Engineer engineer = new Engineer();
        Player player = new Player("Test Player", engineer);
        IslandTile destination = new IslandTile(5);
        engineer.specialMove(player, destination);
        assertEquals(destination, player.getCurrentTile());
    }
}
