package team.group14.forbiddenisland.model;

import lombok.Data;

@Data
public class FloodCard extends Card{
    private double islandName;

    public FloodCard(double islandName) {
        this.type = CardType.FLOOD;
        this.islandName = islandName;
        this.description = "Flood card for island: " + islandName;
    }
}
