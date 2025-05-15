package team.group14.forbiddenisland.model;

import lombok.Data;

@Data
public abstract class Card {
    protected CardType type;
    protected String description;

    public CardType getType() {
        return type;
    }
}
