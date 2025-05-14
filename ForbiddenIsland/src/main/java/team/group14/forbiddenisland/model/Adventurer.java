package team.group14.forbiddenisland.model;

public abstract class Adventurer {
    protected String roleName;
    protected String specialAbilityDescription;

    public String getRoleName() {
        return roleName;
    }

    public String getSpecialAbilityDescription() {
        return specialAbilityDescription;
    }

    // 针对移动、修复、交换牌等操作，可在子类中覆盖
    public void specialMove(Player player, IslandTile destination) {
        // 默认没有特殊移动
        player.move(destination);
    }
}

