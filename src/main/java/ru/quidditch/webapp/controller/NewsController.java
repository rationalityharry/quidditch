package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.entity.OperatorEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.Faculty;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.NewsService;
import ru.quidditch.webapp.data.service.TeamService;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping(value = "/news")
public class NewsController extends AbstractController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, List<NewsDTO>>> getEditInfo(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null) {
            return null;
        }
        Map<String, List<NewsDTO>> result = new HashMap<>();

        List<NewsDTO> news = new LinkedList<>();
        newsService.getNews(teamService.getTeamByFaculty(user.getFaculty())).forEach(newsEntity -> news.add(new NewsDTO(newsEntity)));
        result.put("team", news);

        List<NewsDTO> news1 = new LinkedList<>();
        newsService.getNews(teamService.getTeamByFaculty(Faculty.ALL)).forEach(newsEntity -> news1.add(new NewsDTO(newsEntity)));
        result.put("allNews", news1);

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
}
