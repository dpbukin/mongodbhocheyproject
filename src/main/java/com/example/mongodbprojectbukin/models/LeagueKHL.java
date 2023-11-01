package com.example.mongodbprojectbukin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leagues") // Указываем коллекцию MongoDB для хранения данных
public class LeagueKHL {
    @Id
    private String id; // Идентификатор лиги
    private String conference; // Конференция
    private String division; // Дивизион
    private String team; //команда

    public LeagueKHL(String conference, String division, String team) {
        this.conference = conference;
        this.division = division;
        this.team = team;
    }

    public LeagueKHL() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "LeagueKHL{" +
                "id='" + id + '\'' +
                ", conference='" + conference + '\'' +
                ", division='" + division + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
