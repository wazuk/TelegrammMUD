package game.dataStorage;

import game.world.creatures.playable.Champion;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    private boolean isOnline;

    private String chatId;
    private List<Champion> champions = new ArrayList<>();

    public List<Champion> getChampions() {
        return champions;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private String playerName;

    public Player(String chatId) {
        this.chatId = chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void addChampion(Champion champion){
        champions.add(champion);
    }


}
