package com.example.mongodbprojectbukin.repos;


import com.example.mongodbprojectbukin.models.LeagueKHL;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeagueKHLRepository extends MongoRepository<LeagueKHL, String> {
}
