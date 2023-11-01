package com.example.mongodbprojectbukin.services.impl;

import com.example.mongodbprojectbukin.models.LeagueKHL;
import com.example.mongodbprojectbukin.models.Player;
import com.example.mongodbprojectbukin.models.Team;
import com.example.mongodbprojectbukin.repos.LeagueKHLRepository;
import com.example.mongodbprojectbukin.repos.PlayersRepository;
import com.example.mongodbprojectbukin.repos.TeamRepository;
import com.example.mongodbprojectbukin.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    private PlayersRepository playerRepository;

    @Autowired
    private LeagueKHLRepository leagueKHLRepository;
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team createTeam(Team team) {
        teamRepository.save(team);
        return team;
    }

    @Override
    public Optional<Team> getTeam(String id) {
        return teamRepository.findById(id);
    }

    @Override
    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void saveAllTeams(List<Team> teams) {
        teamRepository.saveAll(teams);
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams;
    }

    @Override
    public Team updateHeadCoach(String id, String headCoach) {
        Team team = teamRepository.findById(id).orElseThrow();
        team.setHeadCoach(headCoach);
        teamRepository.save(team);
        return team;
    }

    @Override
    public Team cascadeDeleteTeamLeaguePlayers(String id) throws Exception {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();

            List<Player> players = team.getPlayers();
            if (players != null && !players.isEmpty()) {
                playerRepository.deleteAll(players);
            }

            LeagueKHL league = team.getLeagueKHL();
            if (league != null) {
                leagueKHLRepository.delete(league);
            }

            teamRepository.delete(team);

            return team;
        } else {
            throw new Exception("Team not found with id: " + id);
        }
    }


}
