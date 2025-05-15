package game.forbiddenisland.model;

import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import static org.junit.jupiter.api.Assertions.*;

public class FloodCardTest {
    @Test
    void testFloodCardInitialize() {
        FloodCard floodCard = new FloodCard(2.5);
        assertEquals(CardType.FLOOD, floodCard.getType(), "FloodCard type should be FLOOD");
        assertEquals(2.5, floodCard.getIslandName(), "FloodCard island name should be 2.5");
        assertTrue(floodCard.getDescription().contains("2.5"),
                "FloodCard description should contain the island name");
    }
}
