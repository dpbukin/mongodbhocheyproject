package com.example.mongodbprojectbukin.services;

import com.example.mongodbprojectbukin.models.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Player createPlayer(Player player);
    Optional<Player> getPlayer(String id);
    void deletePlayer(String id);
    List<Player> getAllPlayers();
    void saveAllPlayers(List<Player> players);
    Player updateTeam(String id, String team);
    List<Player> getPlayersSortedByName();

}
