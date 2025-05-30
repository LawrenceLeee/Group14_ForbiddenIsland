package edu.bdic.forbiddenisland.util;

/**
 * 游戏中会用到的所有图片类型
 */
public enum ImageType {
    ISLAND("island/island_"),
    FLOOD_ISLAND("sunkIsland/sunk_island"),
    TREASURE("treasure/"),
    TREASURE_CARDS("treasureCards/"),
    ICON("back/icon.jpg");

    private final String filename;

    ImageType(String filename) {
        this.filename = filename;
    }

    /** 获取对应的文件名 */
    public String getFilename() {
        return filename;
    }
}
