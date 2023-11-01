package com.example.mongodbprojectbukin.controllers;

import com.example.mongodbprojectbukin.models.Player;
import com.example.mongodbprojectbukin.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;
    @GetMapping("/allplayers")
    List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    Optional<Player> getPlayerById(@PathVariable String id) {
        return playerService.getPlayer(id);
    }

    @DeleteMapping("/delete/{id}")
    String deletePlayer(@PathVariable String id) {
        playerService.deletePlayer(id);
        return "Успех";
    }

    @PostMapping("/addplayer")
    Player addPlayer(@RequestBody Player player){
        return playerService.createPlayer(player);
    }

    @PutMapping("/updateteam/{id}/{team}")
    Player updatePlayerTeam(@PathVariable String id, @PathVariable String team){
        return playerService.updateTeam(id,team);
    }

    @GetMapping("/sortedByName")
    List<Player> getPlayersSortedByName() {
        List<Player> players = playerService.getPlayersSortedByName();
        return players;
    }
}
