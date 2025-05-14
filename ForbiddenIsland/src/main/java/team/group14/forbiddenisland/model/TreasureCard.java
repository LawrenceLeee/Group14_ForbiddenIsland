package team.group14.forbiddenisland.model;

import lombok.Data;

@Data
public class TreasureCard extends Card{
    private Treasure treasureType;
    private SpecialCardType specialType;

    public TreasureCard(Treasure treasureType) {
        this.type = CardType.TREASURE;
        this.treasureType = treasureType;
        this.specialType = null;
        this.description = "Treasure card: " + treasureType;
    }

    public TreasureCard(SpecialCardType specialType) {
        this.type = CardType.SPECIAL;
        this.treasureType = null;
        this.specialType = specialType;
        this.description = "Special card: " + specialType;
    }

    public boolean isSpecial() {
        return specialType != null;
    }
}
