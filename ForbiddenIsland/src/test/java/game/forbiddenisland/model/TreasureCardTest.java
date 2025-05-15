package game.forbiddenisland.model;

import lombok.Data;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class TreasureCardTest {
    @Test
    void testTreasureCardConstructor() {
        TreasureCard treasureCard = new TreasureCard(Treasure.FIRE);
        assertEquals(CardType.TREASURE, treasureCard.getType());
        assertEquals(Treasure.FIRE, treasureCard.getTreasureType());
        assertNull(treasureCard.getSpecialType());
        assertFalse(treasureCard.isSpecial());
        assertTrue(treasureCard.getDescription().contains("FIRE"));
    }

    @Test
    void testTreasureCardSpecialConstructor() {
        TreasureCard treasureCard = new TreasureCard(SpecialCardType.HELICOPTER_LIFT);
        assertEquals(CardType.SPECIAL, treasureCard.getType());
        assertNull(treasureCard.getTreasureType());
        assertEquals(SpecialCardType.HELICOPTER_LIFT, treasureCard.getSpecialType());
        assertTrue(treasureCard.isSpecial());
        assertTrue(treasureCard.getDescription().contains("HELICOPTER_LIFT"));
    }
}
