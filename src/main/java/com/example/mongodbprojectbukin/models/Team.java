package com.example.mongodbprojectbukin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "teams")
public class Team {
    @Id
    private String id; // Идентификатор команды
    private String name; // Название команды
    private int yearFounded; // Год основания
    private String headCoach; // Главный тренер
    private String location; // Местоположение
    private String affiliatedClubs; // Аффилированные клубы

//    @Field("leagueKHL")Для хранения обьектов
    @DBRef
    private LeagueKHL leagueKHL; //Информация о лиге

//    @Field("players") Для хранения обьектов
    @DBRef
    private List<Player> players; // Список игроков в команде

    public Team(String name, int yearFounded, String headCoach, String location, String affiliatedClubs, LeagueKHL leagueKHL, List<Player> players) {
        this.name = name;
        this.yearFounded = yearFounded;
        this.headCoach = headCoach;
        this.location = location;
        this.affiliatedClubs = affiliatedClubs;
        this.leagueKHL = leagueKHL;
        this.players = players;
    }

    public Team() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAffiliatedClubs() {
        return affiliatedClubs;
    }

    public void setAffiliatedClubs(String affiliatedClubs) {
        this.affiliatedClubs = affiliatedClubs;
    }

    public LeagueKHL getLeagueKHL() {
        return leagueKHL;
    }

    public void setLeagueKHL(LeagueKHL leagueKHL) {
        this.leagueKHL = leagueKHL;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", yearFounded=" + yearFounded +
                ", headCoach='" + headCoach + '\'' +
                ", location='" + location + '\'' +
                ", affiliatedClubs='" + affiliatedClubs + '\'' +
                ", leagueKHL=" + leagueKHL +
                ", players=" + players +
                '}';
    }
}
