package com.example.mongodbprojectbukin.controllers;

import com.example.mongodbprojectbukin.models.Team;
import com.example.mongodbprojectbukin.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/allteams")
    List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    Optional<Team> getTeamById(@PathVariable String id) {
        return teamService.getTeam(id);
    }
    @DeleteMapping("/delete/{id}")
    String deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
        return "Успех";
    }

    @PostMapping("/addplayer")
    Team addPlayer(@RequestBody Team team){
        return teamService.createTeam(team);
    }

    @PutMapping("/updateteam/{id}/{headcoach}")
    Team updateHeadCoach(@PathVariable String id, @PathVariable String headcoach){
        return teamService.updateHeadCoach(id,headcoach);
    }

    @DeleteMapping("/cascadeteam/{id}")
    String cascadeDeleteTeam(@PathVariable String id) throws Exception {
        teamService.cascadeDeleteTeamLeaguePlayers(id);
        return "Успех";

    }
}
