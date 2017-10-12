package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntity;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SessionAttributes(value = "user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserEntityService userEntityService;

    @Autowired
    CommentsEntityService commentsEntityService;

    @Autowired
    AnimeEntityService animeEntityService;

    @RequestMapping(value = "user", method = GET)
    public ModelAndView viewUserOptions() {
        return new ModelAndView("user");
    }

    @RequestMapping(value = "/comments", method = GET)
    public ModelAndView viewUserComments() {
        return new ModelAndView("comments");
    }

    @RequestMapping(value = "/comments", method = GET)
    @ResponseBody
    public List<CommentsEntity> getUserComments(@RequestParam(name = "user_id") final long userId) {
        return commentsEntityService.getAllByUser(userEntityService.getAllbyId(userId).get(0));
    }


}
