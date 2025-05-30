package edu.bdic.forbiddenisland.model;

import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private final int playerIndex;
    private final Profession profession;
    private int tileIndex; // 新增：当前逻辑岛屿编号
    private int x, y; // 如果你现在只用tileIndex，xy可以不用

    // 新的构造器，带 tileIndex
    public Player(int playerIndex, Profession profession, int tileIndex) {
        this.playerIndex = playerIndex;
        this.profession = profession;
        this.tileIndex = tileIndex;
    }

    // 保留原有构造器，兼容旧代码（可以删掉或让它调用3参）
    public Player(int playerIndex, Profession profession) {
        this(playerIndex, profession, 0); // 默认0
    }

    public int getPlayerIndex() { return playerIndex; }
    public Profession getProfession() { return profession; }

    public int getTileIndex() { return tileIndex; }
    public void setTileIndex(int tileIndex) { this.tileIndex = tileIndex; }

    // 如果只用tileIndex，可以不需要x,y和moveTo，建议删掉或改成
    public void moveTo(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    private final Queue<TreasureCard> handCards = new LinkedList<>();

    public Queue<TreasureCard> getHandCards() {
        return handCards;
    }

    public void addCard(TreasureCard card) {
        handCards.add(card);
    }

    public void removeCard(TreasureCard card) {
        handCards.remove(card);
    }

    public int handSize() {
        return handCards.size();
    }
}
