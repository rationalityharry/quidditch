package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.entity.OperatorEntity;
import ru.quidditch.webapp.data.entity.PlayerEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.NewsService;
import ru.quidditch.webapp.data.service.PlayerService;
import ru.quidditch.webapp.data.service.TeamService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/news")
public class NewsController extends AbstractController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, List<NewsDTO>>> getEditInfo(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Map<String, List<NewsDTO>> result = new HashMap<>();

        List<NewsDTO> news = newsService.getNews(teamService.getTeamByFaculty(user.getFaculty())).stream().map(NewsDTO::new).collect(Collectors.toCollection(LinkedList::new));
        result.put("team", news);

        List<NewsDTO> news1 = newsService.getNews(teamService.getTeamByFaculty(Faculty.ALL)).stream().map(NewsDTO::new).collect(Collectors.toCollection(LinkedList::new));
        result.put("allNews", news1);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/playersRate")
    public ResponseEntity<List<PlayerDTO>> getPlayersRating(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<PlayerDTO> result = playerService.findPlayersByRating().stream().map(PlayerDTO::new).collect(Collectors.toCollection(LinkedList::new));

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<NewsDTO> createNews(HttpServletRequest request, @RequestBody NewsDTO newsDTO) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (!Roles.OPERATOR.equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        NewsEntity news = new NewsEntity(newsDTO.headline, newsDTO.content);
        news.setTeam(teamService.getTeamByFaculty(Faculty.valueOf(newsDTO.faculty.toUpperCase())));
        news.setDate(new Date());
        news.setOperator((OperatorEntity) user);
        news = newsService.save(news);
        return ResponseEntity.ok(new NewsDTO(news));
    }

    private static class NewsDTO {
        Long id;
        String headline;
        String content;
        String faculty;

        public NewsDTO() {
        }

        NewsDTO(NewsEntity newsEntity) {
            this.id = newsEntity.getId();
            this.headline = newsEntity.getHeadline();
            this.content = newsEntity.getContent();
            this.faculty = null;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }
    }

    private static class PlayerDTO {
        private String name;
        private String surname;
        private Integer rating;
        private String faculty;

        PlayerDTO(PlayerEntity player) {
            this.name = player.getName();
            this.surname = player.getSurname();
            this.rating = player.getRate();
            this.faculty = player.getFaculty().getName();
        }

        public PlayerDTO() {
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }
    }
}
