package team.group14.forbiddenisland.model;

import lombok.Data;

@Data
public class IslandTile {
    private double Id;

    private int place;

    private TileState state;
    // 与宝藏关联，若不关联则为 null
    private Treasure associatedTreasure;


    public IslandTile(double Id) {
        this.Id = Id;
        this.state = TileState.NORMAL;
        this.associatedTreasure = null;
        if (Id == 2 || Id == 3) {
            this.associatedTreasure = Treasure.FIRE;
        } else if (Id == 6 || Id == 20) {
            this.associatedTreasure = Treasure.OCEAN;
        } else if (Id == 11 || Id == 23) {
            this.associatedTreasure = Treasure.WIND;
        } else if (Id == 18 || Id == 19) {
            this.associatedTreasure = Treasure.EARTH;
        }
    }

    public TileState getState() {
        return state;
    }

    // 被洪水影响时调用
    public void flood() {
        if (state == TileState.NORMAL) {
            state = TileState.FLOODED;
        } else if (state == TileState.FLOODED) {
            state = TileState.SUNK;
        }
    }

    // 加固岛屿，恢复到 NORMAL 状态
    public void shoreUp() {
        if (state == TileState.FLOODED) {
            state = TileState.NORMAL;
        }
    }

}

