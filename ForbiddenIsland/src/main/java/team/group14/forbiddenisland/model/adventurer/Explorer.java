package team.group14.forbiddenisland.model.adventurer;

import team.group14.forbiddenisland.model.Adventurer;
import team.group14.forbiddenisland.model.IslandTile;
import team.group14.forbiddenisland.model.Player;

public class Explorer extends Adventurer {
    public Explorer() {
        this.roleName = "Explorer";
        this.specialAbilityDescription = "Can fly to any island once per turn.";
    }

    @Override
    public void specialMove(Player player, IslandTile destination) {
        // 飞行员可以直接飞到任意岛屿
        player.move(destination);
    }
}
