package com.example.mongodbprojectbukin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "players")
public class Player {

    @Id
    private String id; // Идентификатор игрока
    private String number; // Номер игрока
    private String firstLastName; //Имя и фамилия игрока
    private String country; // Страна
    private String hand; // Хват
    private LocalDate dateOfBirth; // Дата рождения
    private double height; // Рост
    private double weight; // Вес
    private String position; //Позиция
    private String team; //Команда

    public Player(String number, String firstLastName, String country, String hand, LocalDate dateOfBirth, double height, double weight, String position, String team) {
        this.number = number;
        this.firstLastName = firstLastName;
        this.country = country;
        this.hand = hand;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.team = team;
    }

    public Player() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", country='" + country + '\'' +
                ", hand='" + hand + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", height=" + height +
                ", weight=" + weight +
                ", position='" + position + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
