package team.group14.forbiddenisland.model;
import lombok.Data;
import team.group14.forbiddenisland.service.Director;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameState {
    private int turnNumber;  // 当前回合数
    private int currentPlayerIndex;  // 当前玩家索引
    private Player currentPlayer; // 当前玩家
    private String phase;  // 当前阶段，例如 "ACTION", "DRAW_TREASURE", "DRAW_FLOOD"
    private boolean gameOver;  // 游戏是否结束
    private boolean victory;  // 是否获胜
    private int actionsRemaining;  // 当前玩家剩余的行动点（每回合最多 3）
    private List<Treasure> collectedTreasures;  // 已收集的宝藏
    private int waterLevel;  // 当前水位等级
    private ActionState actionState;

    // 构造方法，初始化游戏状态
    public GameState() {
        this.turnNumber = 1;
        this.currentPlayerIndex = 0;
        this.currentPlayer = Director.getInstance().getPlayers().get(currentPlayerIndex);
        this.phase = "ACTION";  // 默认从行动阶段开始
        this.gameOver = false;
        this.victory = false;
        this.actionsRemaining = 3;  // 每回合默认 3 次行动
        this.collectedTreasures = new ArrayList<>();
        this.waterLevel = 1;  // 水位初始等级
    }

    // 进入下一回合
    public void nextTurn() {
        turnNumber++;
        currentPlayerIndex = (currentPlayerIndex + 1) % Director.getInstance().getPlayerNum(); // 实现轮转
        currentPlayer = Director.getInstance().getPlayers().get(currentPlayerIndex);
        phase = "ACTION"; // 每回合从行动阶段开始
        actionsRemaining = 3; // 每回合重置行动点
    }

    // 设置游戏结束
    public void endGame(boolean victory) {
        this.gameOver = true;
        this.victory = victory;
    }

    // 记录宝藏收集
    public void collectTreasure(Treasure treasure) {
        if (!collectedTreasures.contains(treasure)) {
            collectedTreasures.add(treasure);
        }
    }

    // 增加水位（因 "水位上升!" 牌导致）
    public void increaseWaterLevel() {
        waterLevel++;
    }

    // 扣除行动点
    public void useAction() {
        if (actionsRemaining > 0) {
            actionsRemaining--;
        }
    }

    // 检查游戏是否胜利或失败
    public boolean isGameOver() {
        if (gameOver || victory) {
            return true;
        }
        return false;
    }

    // 进入下一个阶段
    public void nextPhase() {
        switch (phase) {
            case "ACTION":
                phase = "DRAW_TREASURE"; // 进入抽牌阶段
                break;
            case "DRAW_TREASURE":
                phase = "DRAW_FLOOD"; // 进入抽洪水牌阶段
                break;
            case "DRAW_FLOOD":
                nextTurn(); // 进入下一个玩家回合
                break;
        }
    }
}
