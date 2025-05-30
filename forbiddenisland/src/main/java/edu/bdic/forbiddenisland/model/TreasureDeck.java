package edu.bdic.forbiddenisland.model;

import java.util.ArrayList;
import java.util.List;

public class TreasureDeck extends Deck<TreasureCard> {
    public TreasureDeck() {
        super(makeDeck());
    }

    private static List<TreasureCard> makeDeck() {
        List<TreasureCard> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new TreasureCard(TreasureCardType.EARTH));
            list.add(new TreasureCard(TreasureCardType.FIRE));
            list.add(new TreasureCard(TreasureCardType.OCEAN));
            list.add(new TreasureCard(TreasureCardType.WIND));
        }
        for (int i = 0; i < 3; i++) {
            list.add(new TreasureCard(TreasureCardType.HELICOPTER));
            list.add(new TreasureCard(TreasureCardType.WATERRISE));
        }
        for (int i = 0; i < 2; i++) {
            list.add(new TreasureCard(TreasureCardType.SANDBAG));
        }
        return list;
    }
}
