package com.example.mongodbprojectbukin;

import com.example.mongodbprojectbukin.models.LeagueKHL;
import com.example.mongodbprojectbukin.models.Player;
import com.example.mongodbprojectbukin.models.Team;
import com.example.mongodbprojectbukin.repos.LeagueKHLRepository;
import com.example.mongodbprojectbukin.repos.PlayersRepository;
import com.example.mongodbprojectbukin.repos.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collections;

@SpringBootApplication
public class MongoDbProjectBukinApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MongoDbProjectBukinApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "mongodb"));
        app.run(args);
    }


    @Component
    public class ExcelDataLoader implements CommandLineRunner {

        @Autowired
        private TeamRepository teamRepository;
        @Autowired
        private PlayersRepository playerRepository;
        @Autowired
        private LeagueKHLRepository leagueKHLRepository;

        public ExcelDataLoader(TeamRepository teamRepository, PlayersRepository playerRepository, LeagueKHLRepository leagueKHLRepository) {
            this.teamRepository = teamRepository;
            this.playerRepository = playerRepository;
            this.leagueKHLRepository = leagueKHLRepository;
        }

        @Override
        public void run(String... args) throws  Exception{

//            Очистка старых данных при старте приложения для избежания дублирования значений
            playerRepository.deleteAll();
            teamRepository.deleteAll();
            leagueKHLRepository.deleteAll();

            try (InputStream fileStream = new ClassPathResource("DataHockeyMongoDB.xlsx").getInputStream();
                 Workbook workbook = new XSSFWorkbook(fileStream)) {


                // Читаем лист "Players" и сохраняем игроков
                Sheet playerSheet = workbook.getSheet("players");
                List<Player> players = readPlayersFromExcel(playerSheet);
                playerRepository.saveAll(players);

                // Читаем лист "Leagues" и сохраняем лигу KHL
                Sheet leagueSheet = workbook.getSheet("league");
                List<LeagueKHL> leagues = readLeaguesFromExcel(leagueSheet);
                leagueKHLRepository.saveAll(leagues);

                // Читаем лист "Teams" и сохраняем команды
                Sheet teamSheet = workbook.getSheet("teams");
                List<Team> teams = readTeamsFromExcel(teamSheet);
                teamRepository.saveAll(teams);

                updateTeamsWithPlayers(teams);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private List<Player> readPlayersFromExcel(Sheet sheet) {

            List<Player> players = new ArrayList<>();

            boolean firstRow = true;
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {

//                Пропуск первой строки
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                Player player = new Player();

                Cell cell = row.getCell(0); // Номер игрока
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setNumber(cellValue);
                }

                cell = row.getCell(1); // Имя и фамилия игрока
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setFirstLastName(cellValue);
                }

                cell = row.getCell(2); // Страна
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setCountry(cellValue);
                }

                cell = row.getCell(3); // Хват
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setHand(cellValue);
                }

                cell = row.getCell(4); // Дата рождения
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                       // Разбиваем строку на части по символу '/'
                    String[] parts = cellValue.split("/");

                    int month = Integer.parseInt(parts[0]);
                    int day = Integer.parseInt(parts[1]);
                    int year = Integer.parseInt(parts[2]);

                    // Преобразуем двузначный год в четырехзначный
                    if (year < 100) {
                        if (year >= 0 && year <= 21) {
                            year += 2000; // Если год от 0 до 21, то это 2000-2021
                        } else {
                            year += 1900; // В остальных случаях, это 1900-1999
                        }
                    }

                    LocalDate dateOfBirth = LocalDate.of(year, month, day);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Формат даты
                    String formattedDate = dateOfBirth.format(formatter);

                    player.setDateOfBirth(dateOfBirth);
                    System.out.println("Дата рождения из Excel: " + formattedDate);
                }

                cell = row.getCell(5); // Рост
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setHeight(Double.parseDouble(cellValue));
                }

                cell = row.getCell(6); // Вес
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setWeight(Double.parseDouble(cellValue));
                }

                cell = row.getCell(7); // Позиция
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setPosition(cellValue);
                }

                cell = row.getCell(8); // Столбец с названием команды
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    player.setTeam(cellValue);
                }

                players.add(player);
            }
            return players;
        }

        private List<LeagueKHL> readLeaguesFromExcel(Sheet sheet) {

            List<LeagueKHL> leagues = new ArrayList<>();

            boolean firstRow = true; // Флаг для первой строки (заголовков)
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {

                // Пропуск первой строки
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                LeagueKHL leagueKHL = new LeagueKHL();

                Cell cell = row.getCell(0);
                if (cell != null) {
                    String conference = dataFormatter.formatCellValue(cell);
                    leagueKHL.setConference(conference);
                }

                cell = row.getCell(1);
                if (cell != null) {
                    String division = dataFormatter.formatCellValue(cell);
                    leagueKHL.setDivision(division);
                }

                cell = row.getCell(2);
                if (cell != null) {
                    String teamName = dataFormatter.formatCellValue(cell);
                    leagueKHL.setTeam(teamName);
                }

                leagues.add(leagueKHL);
            }
            return leagues;
        }

        private List<Team> readTeamsFromExcel(Sheet sheet) {
            List<Team> teams = new ArrayList<>();

            boolean firstRow = true;
            DataFormatter dataFormatter = new DataFormatter();

            // Получение всех команд из бд
            List<LeagueKHL> allLeagues = leagueKHLRepository.findAll();
            List<Player> allPlayers = playerRepository.findAll();

            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                Team team = new Team();

                Cell cell = row.getCell(0);
                if (cell != null) {
                    String name = dataFormatter.formatCellValue(cell);
                    team.setName(name);
                }

                cell = row.getCell(1);
                if (cell != null) {
                    String yearFounded = dataFormatter.formatCellValue(cell);
                    team.setYearFounded(Integer.parseInt(yearFounded));
                }

                cell = row.getCell(2);
                if (cell != null) {
                    String headCoach = dataFormatter.formatCellValue(cell);
                    team.setHeadCoach(headCoach);
                }

                cell = row.getCell(3);
                if (cell != null) {
                    String location = dataFormatter.formatCellValue(cell);
                    team.setLocation(location);
                }

                cell = row.getCell(4);
                if (cell != null) {
                    String affiliatedClubs = dataFormatter.formatCellValue(cell);
                    team.setAffiliatedClubs(affiliatedClubs);
                }

                List<Player> teamPlayers = new ArrayList<>();

                cell = row.getCell(8); // Столбец с названием команды
                if (cell != null) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    for (Player player : allPlayers) {
                        if (player.getTeam().equals(cellValue)) {
                            // Добавляем игроков в списов игроков команды
                            teamPlayers.add(player);
                        }
                    }
                }

                // Устанавливаем список игроков в команду
                team.setPlayers(teamPlayers);

                // Связка команды с лигой
                for (LeagueKHL league : allLeagues) {
                    if (league.getTeam().equals(team.getName())) {
                        team.setLeagueKHL(league);
                        break; // После нахождения лиги выходим из цикла
                    }
                }

                teams.add(team);
            }

            return teams;
        }


        private void updateTeamsWithPlayers(List<Team> teams) {
            // Получаем список игроков из бд
            List<Player> allPlayers = playerRepository.findAll();

            // Обновляем команды, добывив игроков
            for (Team team : teams) {

                List<Player> teamPlayers = new ArrayList<>();

                for (Player player : allPlayers) {

                    if (player.getTeam().equals(team.getName())) {
                        teamPlayers.add(player);
                    }
                }
                team.setPlayers(teamPlayers);
            }

            // Сохранение обновленных команд
            teamRepository.saveAll(teams);
        }
    }
}

