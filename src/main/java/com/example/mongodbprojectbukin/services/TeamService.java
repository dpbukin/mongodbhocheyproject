package com.example.mongodbprojectbukin.services;

import com.example.mongodbprojectbukin.models.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team createTeam(Team team);
    Optional<Team> getTeam(String id);
    void deleteTeam(String id);
    void saveAllTeams(List<Team> teams);
    List<Team> getAllTeams();
    Team updateHeadCoach(String id, String headCoach);
    Team cascadeDeleteTeamLeaguePlayers(String id) throws Exception;


}
