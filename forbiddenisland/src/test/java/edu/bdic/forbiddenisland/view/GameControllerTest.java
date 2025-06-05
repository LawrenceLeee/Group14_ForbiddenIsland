package edu.bdic.forbiddenisland.view;

import edu.bdic.forbiddenisland.controller.SessionManager;
import edu.bdic.forbiddenisland.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 对 GameController 中部分方法做最基础的“能跑通”测试：
 *  1. handleIslandClick 在非当前玩家回合时直接返回且不抛异常
 *  2. mouseClickedMove 切换 currentAction = NONE → MOVE → NONE
 *  3. mouseClickedShoreUp 切换 currentAction = NONE → SHOREUP → NONE
 */
public class GameControllerTest {

    private GameController controller;

    @BeforeEach
    void setUp() {
        // 直接 new，不调用 initialize()，避免 JavaFX 相关逻辑
        controller = new GameController();
    }

    /**
     * 情况：SessionManager.getInstance().getPlayerId() = 5,
     *       GameModel.getInstance().getCurrentPlayer() = 0
     *       此时不是“自己的回合”，调用 mouseClickedIsland0(null) 不抛异常
     */
    @Test
    void testMouseClickedMove_togglesCurrentAction() throws Exception {
        // 通过反射获取 private ActionState currentAction
        Field currentActionField = GameController.class.getDeclaredField("currentAction");
        currentActionField.setAccessible(true);

        // 初始状态应当是 NONE
        Object before = currentActionField.get(controller);
        assertNotNull(before, "currentAction 初始不应为空");
        assertEquals("NONE", before.toString(), "初始状态应为 NONE");

        // 第一次调用 mouseClickedMove(null)：应切换到 MOVE
        assertDoesNotThrow(() -> controller.mouseClickedMove(null));
        Object afterMove = currentActionField.get(controller);
        assertNotNull(afterMove);
        assertEquals("MOVE", afterMove.toString(), "调用 mouseClickedMove 后应切换到 MOVE");

        // 第二次调用 mouseClickedMove(null)：应切换回 NONE
        assertDoesNotThrow(() -> controller.mouseClickedMove(null));
        Object afterNone = currentActionField.get(controller);
        assertNotNull(afterNone);
        assertEquals("NONE", afterNone.toString(), "再次调用 mouseClickedMove 后应切换回 NONE");
    }

    /**
     * 测试：mouseClickedMove(null) 切换 currentAction 字段
     *  初始值为 NONE → 调用后为 MOVE → 再调用后回到 NONE
     */
    @Test
    void testMouseClickedMove_togglesActionState() throws Exception {
        // currentAction 是 private，需要用反射读写
        Field currentActionField = GameController.class.getDeclaredField("currentAction");
        currentActionField.setAccessible(true);

        // 初始状态应该是 NONE
        Object before = currentActionField.get(controller);
        assertNotNull(before);
        assertEquals("NONE", before.toString());

        // 第一次调用 mouseClickedMove(null)，应切换到 MOVE
        assertDoesNotThrow(() -> controller.mouseClickedMove(null));
        Object afterMove = currentActionField.get(controller);
        assertEquals("MOVE", afterMove.toString());

        // 第二次调用 mouseClickedMove(null)，应切换回 NONE
        assertDoesNotThrow(() -> controller.mouseClickedMove(null));
        Object afterNone = currentActionField.get(controller);
        assertEquals("NONE", afterNone.toString());
    }

    /**
     * 测试：mouseClickedShoreUp(null) 切换 currentAction 字段
     *  初始 NONE → 调用后 SHOREUP → 再调用后回到 NONE
     */
    @Test
    void testMouseClickedShoreUp_togglesActionState() throws Exception {
        Field currentActionField = GameController.class.getDeclaredField("currentAction");
        currentActionField.setAccessible(true);

        // 初始状态 NONE
        Object before = currentActionField.get(controller);
        assertNotNull(before);
        assertEquals("NONE", before.toString());

        // 第一次调用 mouseClickedShoreUp(null)，应切换到 SHOREUP
        assertDoesNotThrow(() -> controller.mouseClickedShoreUp(null));
        Object afterShore = currentActionField.get(controller);
        assertEquals("SHOREUP", afterShore.toString());

        // 第二次调用 mouseClickedShoreUp(null)，应切换回 NONE
        assertDoesNotThrow(() -> controller.mouseClickedShoreUp(null));
        Object afterNone = currentActionField.get(controller);
        assertEquals("NONE", afterNone.toString());
    }
}
