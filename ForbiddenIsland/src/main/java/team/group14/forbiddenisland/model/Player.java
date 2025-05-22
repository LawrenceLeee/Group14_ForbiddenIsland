package team.group14.forbiddenisland.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {
    private String name;
    private Adventurer adventurer;
    private List<TreasureCard> hand;
    private IslandTile currentTile;

    public Player(String name, Adventurer adventurer) {
        this.name = name;
        this.adventurer = adventurer;
        this.hand = new ArrayList<>();
    }

    // 移动到相邻岛屿
    public void move(IslandTile destination) {
        // 检查相邻关系及特殊角色能力
        this.currentTile = destination;
    }

    // 加固岛屿
    public void shoreUp(IslandTile tile) {
        // 调用岛屿的修复方法
        tile.shoreUp();
    }

    // 交换卡牌给其他玩家
    public void giveCard(Player target, TreasureCard card) {
        if (this.hand.contains(card)) {
            this.hand.remove(card);
            target.hand.add(card);
        }
    }

    // 获取宝藏：消耗 4 张相同宝藏牌（在对应岛屿上）
    public boolean captureTreasure(Treasure treasure) {
        // 检查手牌中是否有足够的宝藏牌
        // 若条件满足，则移除这些牌，并通知游戏状态更新宝藏获取情况
        return false;
    }

    // 添加手牌
    public void addCard(TreasureCard card) {
        hand.add(card);
        // 如果超过上限，则需要处理弃牌逻辑
    }
}
