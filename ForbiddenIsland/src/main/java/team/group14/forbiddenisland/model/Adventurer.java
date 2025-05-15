package team.group14.forbiddenisland.model;

import lombok.Data;

@Data
public abstract class Adventurer {
    protected String roleName;
    protected String specialAbilityDescription;

    // 针对移动、修复、交换牌等操作，可在子类中覆盖
    public void specialMove(Player player, IslandTile destination) {
        // 默认没有特殊移动
        player.move(destination);
    }
}

