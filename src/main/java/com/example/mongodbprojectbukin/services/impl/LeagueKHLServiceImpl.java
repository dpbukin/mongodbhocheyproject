package com.example.mongodbprojectbukin.services.impl;

import com.example.mongodbprojectbukin.models.LeagueKHL;
import com.example.mongodbprojectbukin.repos.LeagueKHLRepository;
import com.example.mongodbprojectbukin.services.LeagueKHLService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueKHLServiceImpl implements LeagueKHLService {

    private final LeagueKHLRepository leagueKHLRepository;

    public LeagueKHLServiceImpl(LeagueKHLRepository leagueKHLRepository) {
        this.leagueKHLRepository = leagueKHLRepository;
    }


    @Override
    public LeagueKHL createLeagueKHL(LeagueKHL leagueKHL) {
        return leagueKHLRepository.save(leagueKHL);
    }

    @Override
    public Optional<LeagueKHL> getLeagueKHL(String id) {
        return leagueKHLRepository.findById(id);
    }

    @Override
    public void deleteLeagueKHL(String id) {
        leagueKHLRepository.deleteById(id);
    }

    @Override
    public List<LeagueKHL> getAllLeagueKHL() {
        List<LeagueKHL> leagues = leagueKHLRepository.findAll();
        return leagues;
    }

    @Override
    public void saveAllLeagueKHL(List<LeagueKHL> leagueKHLS) {
        leagueKHLRepository.saveAll(leagueKHLS);
    }

    @Override
    public LeagueKHL updateTeam(String id, String team) {
        LeagueKHL leagueKHL = leagueKHLRepository.findById(id).orElseThrow();
        leagueKHL.setTeam(team);
        leagueKHLRepository.save(leagueKHL);
        return leagueKHL;
    }
}
