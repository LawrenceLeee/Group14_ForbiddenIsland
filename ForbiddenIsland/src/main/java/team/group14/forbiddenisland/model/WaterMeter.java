package team.group14.forbiddenisland.model;

public class WaterMeter {
    private int level;  // 当前水位等级

    // 定义不同水位下抽取洪水牌的数量（例如：Novice=2, Normal=3, Elite=4, Legendary=5）
    private int[] floodDrawNumbers = {2, 3, 4, 5};

    public WaterMeter(int initialLevel) {
        this.level = initialLevel;
    }

    public int getLevel() {
        return level;
    }

    // 水位上升
    public void increaseLevel() {
        level++;
    }

    // 根据当前水位返回抽取洪水牌数量
    public int getFloodDrawCount() {
        // 根据难度，可能需要判断 level 是否超出数组范围
        if (level < floodDrawNumbers.length) {
            return floodDrawNumbers[level];
        }
        return floodDrawNumbers[floodDrawNumbers.length - 1];
    }
}
