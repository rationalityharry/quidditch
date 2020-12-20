package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.GameEntity;
import ru.quidditch.webapp.data.entity.TeamEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.GameService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GamesController extends AbstractController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<GamesController.GameDTO>> getEditInfo(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<GamesController.GameDTO> result = new ArrayList<>();

       gameService.getGames(new Date()).forEach(game->
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
        gameEntity.setDate(game.date);
        gameEntity.setTime(game.time);
        gameEntity.setLocation(game.getLocation());
        gameEntity.setTeam1(game.team1);
        gameEntity.setTeam2(game.team2);
        gameEntity = gameService.save(gameEntity);
        return ResponseEntity.ok(new GameDTO(gameEntity));

    }

    private static class GameDTO {

        private TeamEntity team1;
        private TeamEntity team2;
        private String location;
        private Date date;
        private Date time;

        public GameDTO() {
        }

        public GameDTO(GameEntity game) {
            this.team1 = game.getTeam1();
            this.team2 = game.getTeam2();
            this.location = game.getLocation();
            this.date = game.getDate();
            this.time = game.getTime();
        }

        public TeamEntity getTeam1() {
            return team1;
        }

        public void setTeam1(TeamEntity team1) {
            this.team1 = team1;
        }

        public TeamEntity getTeam2() {
            return team2;
        }

        public void setTeam2(TeamEntity team2) {
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
    }
}