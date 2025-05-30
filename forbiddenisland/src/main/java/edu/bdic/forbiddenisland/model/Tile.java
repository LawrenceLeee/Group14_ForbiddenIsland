package edu.bdic.forbiddenisland.model;

public class Tile {
    private final int x;
    private final int y;
    private TileStatus status;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = TileStatus.NORMAL;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileStatus getStatus() {
        return status;
    }

    public void setStatus(TileStatus status) {
        this.status = status;
    }
}
