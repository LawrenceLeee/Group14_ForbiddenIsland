package edu.bdic.forbiddenisland.network.handler.impl;

import edu.bdic.forbiddenisland.model.GameModel;
import edu.bdic.forbiddenisland.network.Message;
import edu.bdic.forbiddenisland.network.MessageType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class NextTurnHandlerTest {

    @Test
    void testHandleInvokesGameModelEndTurn() {
        // 1. 构造一个假的 Message，类型为 NEXT_TURN
        Message fakeMsg = new Message(
                MessageType.NEXT_TURN,
                "sessionNT",
                1,
                null
        );

        // 2. Mock GameModel.getInstance() 返回一个 mockModel
        GameModel mockModel = mock(GameModel.class);

        try (MockedStatic<GameModel> gmStatic = mockStatic(GameModel.class)) {
            gmStatic.when(GameModel::getInstance).thenReturn(mockModel);

            // 3. 调用 handler.handle(fakeMsg)
            NextTurnHandler handler = new NextTurnHandler();
            handler.handle(fakeMsg);

            // 4. 验证 GameModel.endTurn() 被调用一次
            verify(mockModel, times(1)).endTurn();
        }
    }
}
