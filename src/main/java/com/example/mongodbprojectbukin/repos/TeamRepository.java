package com.example.mongodbprojectbukin.repos;

import com.example.mongodbprojectbukin.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {

}
