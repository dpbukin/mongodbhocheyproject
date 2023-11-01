package com.example.mongodbprojectbukin.services;

import com.example.mongodbprojectbukin.models.LeagueKHL;

import java.util.List;
import java.util.Optional;

public interface LeagueKHLService {
    LeagueKHL createLeagueKHL(LeagueKHL leagueKHL);
    Optional<LeagueKHL> getLeagueKHL(String id);
    void deleteLeagueKHL(String id);
    List<LeagueKHL> getAllLeagueKHL();
    void saveAllLeagueKHL(List<LeagueKHL> leagueKHLS);
    LeagueKHL updateTeam(String id, String team);


}
