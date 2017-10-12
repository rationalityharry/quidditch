package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntity;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntity;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/comments")
public class UserController {
    @Autowired
    UserEntityService userEntityService;

    @Autowired
    CommentsEntityService commentsEntityService;

    @Autowired
    AnimeEntityService animeEntityService;

    @RequestMapping(value = "comments", method = GET)
    public ModelAndView viewOpinions() {
        return new ModelAndView("comments");
    }

    @RequestMapping(value = "/{animeId}", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentsEntity> viewOpinions2(@PathVariable(value = "animeId") final Long animeId) {
        final AnimeEntity oneById = animeEntityService.findOneById(animeId);
        final List<CommentsEntity> allByAnime = commentsEntityService.getAllByAnime(oneById);
        return allByAnime;
    }


}
