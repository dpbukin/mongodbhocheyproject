package com.example.mongodbprojectbukin.services.impl;

import com.example.mongodbprojectbukin.models.Player;
import com.example.mongodbprojectbukin.repos.PlayersRepository;
import com.example.mongodbprojectbukin.services.PlayerService;
import org.springframework.stereotype.Service;


import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayersRepository playersRepository;

    public PlayerServiceImpl(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    public Player createPlayer(Player player) {
        playersRepository.save(player);
        return player;
    }

    @Override
    public Optional<Player> getPlayer(String id) {
        return playersRepository.findById(id);
    }

    @Override
    public void deletePlayer(String id) {
        playersRepository.deleteById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = playersRepository.findAll();
        return players;
    }

    @Override
    public void saveAllPlayers(List<Player> players) {
        playersRepository.saveAll(players);
    }

    @Override
    public Player updateTeam(String id, String team) {
        Player player = playersRepository.findById(id).orElseThrow();
        player.setTeam(team);
        playersRepository.save(player);
        return player;
    }
    @Override
    public List<Player> getPlayersSortedByName() {
        return playersRepository.getPlayersSortedByName();
    }
}
