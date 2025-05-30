package edu.bdic.forbiddenisland.model;

import edu.bdic.forbiddenisland.model.TreasureCardType;
import javafx.scene.image.Image;

public class TreasureCard {
    private final TreasureCardType type;

    public TreasureCard(TreasureCardType type) {
        this.type = type;
    }

    public TreasureCardType getType() {
        return type;
    }

    public Image getFxImage() {
        String path = "/team/group/myforbidden/images/treasureCards/" + type.getFileName();
        return new Image(getClass().getResourceAsStream(path));
    }

    @Override
    public String toString() {
        return type.name();
    }
}
