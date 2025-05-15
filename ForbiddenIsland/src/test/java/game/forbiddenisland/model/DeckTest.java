package game.forbiddenisland.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck<TreasureCard> deck;
    private TreasureCard card1;
    private TreasureCard card2;

    @BeforeEach
    void setUp() {
        card1 = new TreasureCard(Treasure.FIRE);
        card2 = new TreasureCard(Treasure.WIND);
        List<TreasureCard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        deck = new Deck<>(cards); //自动洗牌
    }

    @Test
    void testDraw() {
        TreasureCard drawnCard = deck.draw();
        assertNotNull(drawnCard);
        assertEquals(1, deck.size());
    }

    @Test
    void testDrawToEmpty() {
        deck.draw(); // Draw the first card
        deck.draw(); // Draw the second card
        assertNull(deck.draw());
        assertEquals(0, deck.size());
    }

    @Test
    void testAddCard() {
        TreasureCard newCard = new TreasureCard(Treasure.OCEAN);
        deck.addCard(newCard);
        assertEquals(3, deck.size());
    }
}
