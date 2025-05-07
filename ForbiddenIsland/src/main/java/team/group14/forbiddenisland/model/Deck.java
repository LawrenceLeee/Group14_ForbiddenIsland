package team.group14.forbiddenisland.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Card> {
    private List<T> cards;

    public Deck(List<T> cards) {
        this.cards = new ArrayList<>(cards);
        shuffle();
    }

    // 洗牌
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // 抽牌
    public T draw() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    // 添加卡牌到牌堆（例如用于重建牌堆）
    public void addCard(T card) {
        cards.add(card);
    }

    // 返回当前牌数
    public int size() {
        return cards.size();
    }
}

