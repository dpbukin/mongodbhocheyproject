package com.example.mongodbprojectbukin.repos;

import com.example.mongodbprojectbukin.models.Player;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayersRepository extends MongoRepository<Player, String> {

    @Aggregation(pipeline = {
            "{ $sort: { firstLastName: 1 } }", // Сортировка по имени в алфавитном порядке
            "{ $project: { number: 1, firstLastName: 1, country: 1, hand: 1, dateOfBirth: 1, height: 1, weight: 1, position: 1, team: 1 } }",
            "{ $limit: 100 }" // Ограничение количества результатов
    })
    List<Player> getPlayersSortedByName();
}
