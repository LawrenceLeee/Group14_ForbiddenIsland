package game.forbiddenisland.model.adventurer;

import lombok.Data;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.IslandTile;
import team.group14.forbiddenisland.model.Player;
import team.group14.forbiddenisland.model.adventurer.Diver;

import static org.junit.jupiter.api.Assertions.*;

public class DiverTest {
    @Test
    void testRoleNameAndAbility() {
        Diver diver = new Diver();
        assertEquals("Diver", diver.getRoleName());
        assertTrue(diver.getSpecialAbilityDescription().contains("fly"));
    }

    @Test
    void testSpecialMove() {
        Diver diver = new Diver();
        Player player = new Player("Test Player", diver);
        IslandTile destination = new IslandTile(5);
        diver.specialMove(player, destination);
        assertEquals(destination, player.getCurrentTile());
    }
}
