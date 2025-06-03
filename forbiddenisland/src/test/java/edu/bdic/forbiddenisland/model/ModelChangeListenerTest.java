package edu.bdic.forbiddenisland.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelChangeListenerTest {

    @Test
    void testOnModelChangedCallbackInvoked() {
        // 使用匿名内部类或 lambda 创建一个实现 ModelChangeListener 的实例，记录是否被调用
        final boolean[] called = {false};
        ModelChangeListener listener = () -> called[0] = true;

        // 初始时 flag 应为 false
        assertFalse(called[0], "调用前 flag 应为 false");

        // 触发回调
        listener.onModelChanged();

        // 调用后 flag 应为 true
        assertTrue(called[0], "调用后 flag 应为 true");
    }
}
