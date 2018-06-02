package game.dataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {

    private List<String> playerIds = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }



    private List<Player> players = new ArrayList<>();

    public Map<String, Player> getPlayerMap() {
        return playerMap;
    }

    private Map<String,Player> playerMap = new HashMap<>();

    public List<String> getPlayerIds() {
        return playerIds;
    }

    public void addPlayerId(String string){
        playerIds.add(string);
    }

    public void addPlayer(String playerId){
        Player player = new Player(playerId);
        players.add(player);
        playerMap.put(playerId,player);
    }
}
