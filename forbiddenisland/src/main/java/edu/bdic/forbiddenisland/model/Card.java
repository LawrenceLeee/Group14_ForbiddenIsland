package edu.bdic.forbiddenisland.model;

/**
 * 洪水牌：仅封装一个 tileIndex，用于 floodDeck
 */
public class Card {
    private final int tileIndex;

    public Card(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    @Override
    public String toString() {
        return "Card{" + "tileIndex=" + tileIndex + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return tileIndex == card.tileIndex;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(tileIndex);
    }
}
