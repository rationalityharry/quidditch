package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

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

  /*  @RequestMapping(value = "comments", method = GET)
    @ResponseBody
    public String getAllByUserId() {
        return commentsEntityService.getAllByUser(userEntityService.getAllByLogin(login));
    }
*/

}
