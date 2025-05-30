package edu.bdic.forbiddenisland.server;

import io.netty.channel.Channel;
import edu.bdic.forbiddenisland.model.Profession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameRoomManager {
    private static final GameRoomManager INSTANCE = new GameRoomManager();

    private final Map<String, Set<Channel>> rooms = new ConcurrentHashMap<>();
    private final Map<Channel, String> chToSession = new ConcurrentHashMap<>();
    private final Map<String, List<Integer>> layouts = new ConcurrentHashMap<>();
    private final Map<String, Map<Integer, Profession>> sessionProfessions = new ConcurrentHashMap<>();

    private GameRoomManager() {}
    public static GameRoomManager getInstance() { return INSTANCE; }

    /**
     * 创建新房间：生成 sessionId，初始化连接集合、岛屿布局和职业队列
     */
    public String createRoom() {
        String sessionId = UUID.randomUUID().toString();
        rooms.putIfAbsent(sessionId, new CopyOnWriteArraySet<>());

        // 缓存一次乱序布局
        List<Integer> layout = IntStream.range(0, 24).boxed().collect(Collectors.toList());
        Collections.shuffle(layout);
        layouts.put(sessionId, layout);

        // 初始化职业表
        sessionProfessions.put(sessionId, new ConcurrentHashMap<>());

        System.out.println("[GameRoomManager DEBUG] createRoom → sessionId=" + sessionId + ", layout=" + layout);
        return sessionId;
    }

    /** 获得房间的布局 */
    public List<Integer> getLayoutForSession(String sessionId) {
        List<Integer> layout = layouts.get(sessionId);
        System.out.println("[GameRoomManager DEBUG] getLayoutForSession → sessionId=" + sessionId + ", layout=" + layout);
        return layout;
    }

    /** 玩家加入房间 */
    public void join(String sessionId, Channel ch) {
        rooms.computeIfAbsent(sessionId, k -> new CopyOnWriteArraySet<>()).add(ch);
        chToSession.put(ch, sessionId);
        System.out.println("[GameRoomManager DEBUG] join → sessionId=" + sessionId + ", channel=" + ch + ", totalPlayers=" + rooms.get(sessionId).size());
    }

    /** 给房间里已有玩家数作为 next playerIndex */
    public int getNextPlayerIndex(String sessionId) {
        int next = rooms.getOrDefault(sessionId, Collections.emptySet()).size();
        System.out.println("[GameRoomManager DEBUG] getNextPlayerIndex → sessionId=" + sessionId + ", nextIndex=" + next);
        return next;
    }

    /** 给新玩家分配职业，并缓存 */
    public Profession assignNextProfession(String sessionId) {
        // 确保有 profession map
        sessionProfessions.computeIfAbsent(sessionId, k -> new ConcurrentHashMap<>());
        Map<Integer, Profession> profMap = sessionProfessions.get(sessionId);

        Profession[] all = Profession.values();
        Set<Profession> used = new HashSet<>(profMap.values());
        Profession pick;
        do {
            pick = all[new Random().nextInt(all.length)];
        } while (used.contains(pick) && used.size() < all.length);

        System.out.println("[GameRoomManager DEBUG] assignNextProfession → sessionId=" + sessionId + ", pick=" + pick + ", used=" + used);
        return pick;
    }

    /** 缓存某玩家的职业 */
    public void recordProfession(String sessionId, int playerIndex, Profession prof) {
        sessionProfessions.computeIfAbsent(sessionId, k -> new ConcurrentHashMap<>())
                .put(playerIndex, prof);
        System.out.println("[GameRoomManager DEBUG] recordProfession → sessionId=" + sessionId + ", playerIndex=" + playerIndex + ", profession=" + prof);
    }

    /** 广播给房间里所有人（包括自己） */
    public void broadcastToRoom(String sessionId, String msgJson) {
        System.out.println("[GameRoomManager DEBUG] broadcastToRoom → sessionId=" + sessionId + ", msg=" + msgJson);
        for (Channel ch : rooms.getOrDefault(sessionId, Collections.emptySet())) {
            if (ch.isActive()) {
                ch.writeAndFlush(msgJson);
                System.out.println("[GameRoomManager DEBUG]   sent to channel=" + ch);
            }
        }
    }

    /** 返回当前房间所有已分配的玩家职业表 */
    public Map<Integer, Profession> getSessionProfessions(String sessionId) {
        Map<Integer, Profession> profs = sessionProfessions.get(sessionId);
        System.out.println("[GameRoomManager DEBUG] getSessionProfessions → sessionId=" + sessionId + ", professions=" + profs);
        return profs;
    }

    /** 供 ServerHandler 根据 Channel 找回 sessionId */
    public String getSessionFor(Channel ch) {
        String sid = chToSession.get(ch);
        System.out.println("[GameRoomManager DEBUG] getSessionFor → channel=" + ch + ", sessionId=" + sid);
        return sid;
    }

    /** 供 ServerHandler 获取房间内所有通道 */
    public Set<Channel> getChannels(String sessionId) {
        Set<Channel> set = rooms.getOrDefault(sessionId, Collections.emptySet());
        System.out.println("[GameRoomManager DEBUG] getChannels → sessionId=" + sessionId + ", channelsCount=" + set.size());
        return set;
    }
}
