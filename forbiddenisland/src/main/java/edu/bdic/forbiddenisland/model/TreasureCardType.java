package edu.bdic.forbiddenisland.model;

public enum TreasureCardType {
    EARTH("earth.png"),
    FIRE("fire.png"),
    OCEAN("ocean.png"),
    WIND("wind.png"),
    HELICOPTER("helicopter.png"),
    SANDBAG("sandbag.png"),
    WATERRISE("waterRise.png");

    private final String fileName;

    TreasureCardType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
