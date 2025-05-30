package edu.bdic.forbiddenisland.model;

import java.util.*;

/**
 * 泛型牌堆：内部维护抽牌堆和弃牌堆，
 * 当抽牌堆空时，可以把弃牌堆洗回去。
 */
public class Deck<T> {
    private final Deque<T> drawPile;
    private final List<T> discardPile;

    public Deck(List<T> cards) {
        // caller 已经按随机种子 shuffle 过
        this.drawPile = new LinkedList<>(cards);
        this.discardPile = new ArrayList<>();
    }

    /** 主动把弃牌堆洗回抽牌堆 */
    public void recycleDiscards() {
        if (!discardPile.isEmpty()) {
            drawPile.addAll(discardPile);
            discardPile.clear();
            shuffle();
        }
    }

    /** 抽一张，如果抽牌堆空了先把弃牌堆洗回 */
    public T draw() {
        if (drawPile.isEmpty()) {
            recycleDiscards();
        }
        return drawPile.poll();  // poll 可能返回 null
    }

    /** 把一张牌放到内部弃牌堆 */
    public void discard(T card) {
        discardPile.add(card);
    }

    /** 手动洗牌（随机化） */
    public void shuffle() {
        List<T> tmp = new ArrayList<>(drawPile);
        Collections.shuffle(tmp);
        drawPile.clear();
        drawPile.addAll(tmp);
    }

    /** 让调用方能知道抽牌堆此刻是否空 */
    public boolean isEmpty() {
        return drawPile.isEmpty();
    }
}
