package ru.quidditch.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.entity.UserEntity;
import ru.quidditch.webapp.data.enums.NewsTypes;
import ru.quidditch.webapp.data.enums.Roles;
import ru.quidditch.webapp.data.service.NewsService;
import ru.quidditch.webapp.data.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/all")
    public ResponseEntity<List<NewsEntity>> getEditInfo(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (user == null){
            return null;
        }

       /* result.put(NewsTypes.CLUB, user.getTeam().getTeamNews().subList(0,3));*/

        return ResponseEntity.ok(newsService.getNews(NewsTypes.TOURNAMENT));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<NewsEntity> createNews(HttpServletRequest request, @RequestBody Map<String, String> params){
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        if (!Roles.OPERATOR.equals(user.getRole())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body( null);
        }
        NewsEntity news = new NewsEntity(params.get("headline"), params.get("content"));
        news.setNewsType(NewsTypes.TOURNAMENT);
        news.setDate(new Date());
        news.setOperator(null);
        news = newsService.save(news);
        return ResponseEntity.ok(news);

    }




}
