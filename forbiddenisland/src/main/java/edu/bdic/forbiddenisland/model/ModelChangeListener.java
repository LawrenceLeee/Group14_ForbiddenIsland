package edu.bdic.forbiddenisland.model;

public interface ModelChangeListener {
    /** 当玩家位置、地块状态等发生变化时触发 */
    void onModelChanged();
}
