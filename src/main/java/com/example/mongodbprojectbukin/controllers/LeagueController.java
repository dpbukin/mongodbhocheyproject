package com.example.mongodbprojectbukin.controllers;

import com.example.mongodbprojectbukin.models.LeagueKHL;
import com.example.mongodbprojectbukin.services.LeagueKHLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/league")
public class LeagueController {
    @Autowired
    LeagueKHLService leagueKHLService;

    @GetMapping("/allleagueKHL")
    List<LeagueKHL> getAllLeagueKHL() {
        return leagueKHLService.getAllLeagueKHL();
    }

    @GetMapping("/{id}")
    Optional<LeagueKHL> getLeagueKHLById(@PathVariable String id) {
        return leagueKHLService.getLeagueKHL(id);
    }

    @DeleteMapping("/delete/{id}")
    String deleteLeagueKHL(@PathVariable String id) {
        leagueKHLService.deleteLeagueKHL(id);
        return "Успех";
    }

    @PostMapping("/addleague")
    LeagueKHL addLeagueKHL(@RequestBody LeagueKHL leagueKHL){
        return leagueKHLService.createLeagueKHL(leagueKHL);
    }

    @PutMapping("/updateteam/{id}/{team}")
    LeagueKHL updateTeam(@PathVariable String id, @PathVariable String team){
        return leagueKHLService.updateTeam(id,team);
    }


}
