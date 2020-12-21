package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.GameEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.GameService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GamesController extends AbstractController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<GameDTO>> getGames(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<GameDTO> result = new ArrayList<>();

        gameService.getGames(new Date()).forEach(game ->
                result.add(new GameDTO(game))
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveEnded(HttpServletRequest request, @RequestBody GameDTO game) {

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (checkUserNull(user, Roles.OPERATOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        GameEntity gameEntity = gameService.getGameById(game.id);
        gameEntity.setTeam1Score(game.team1Score);
        gameEntity.setTeam2Score(game.team2Score);
        gameService.save(gameEntity);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/ended")
    public ResponseEntity<List<GamesController.GameDTO>> getEndedGames(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<GamesController.GameDTO> result = new ArrayList<>();

        gameService.getEndedGames(new Date()).forEach(game ->
                result.add(new GameDTO(game))
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<GameDTO> createGame(HttpServletRequest request, @RequestBody GameDTO game) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (checkUserNull(user, Roles.OPERATOR)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(game.id);
        gameEntity.setDate(game.date);
        gameEntity.setTime(game.time);
        gameEntity.setLocation(game.getLocation());
        gameEntity.setTeam1(game.team1);
        gameEntity.setTeam2(game.team2);
        gameEntity = gameService.save(gameEntity);
        return ResponseEntity.ok(new GameDTO(gameEntity));
    }

    private static class GameDTO {
        private Long id;
        private String team1;
        private String team2;
        private String location;
        private Date date;
        private String dateFormatted;
        private Date time;
        private String timeFormatted;
        private Integer team1Score;
        private Integer team2Score;

        public GameDTO() {
        }

        GameDTO(GameEntity game) {
            this.id = game.getId();
            this.team1 = game.getTeam1();
            this.team2 = game.getTeam2();
            this.location = game.getLocation();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            this.date = game.getDate();
            this.dateFormatted = sdf.format(this.date);
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            this.time = game.getTime();
            this.timeFormatted = sdfTime.format(this.time);
            this.team1Score = game.getTeam1Score();
            this.team2Score = game.getTeam2Score();
        }

        public String getTeam1() {
            return team1;
        }

        public void setTeam1(String team1) {
            this.team1 = team1;
        }

        public String getTeam2() {
            return team2;
        }

        public void setTeam2(String team2) {
            this.team2 = team2;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDateFormatted() {
            return dateFormatted;
        }

        public void setDateFormatted(String dateFormatted) {
            this.dateFormatted = dateFormatted;
        }

        public String getTimeFormatted() {
            return timeFormatted;
        }

        public void setTimeFormatted(String timeFormatted) {
            this.timeFormatted = timeFormatted;
        }

        public Integer getTeam1Score() {
            return team1Score;
        }

        public void setTeam1Score(Integer team1Score) {
            this.team1Score = team1Score;
        }

        public Integer getTeam2Score() {
            return team2Score;
        }

        public void setTeam2Score(Integer team2Score) {
            this.team2Score = team2Score;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}