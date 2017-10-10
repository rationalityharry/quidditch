package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.animebase.AnimeEntityService;
import ru.sofitlabs.firstwebapp.data.animebase.CommentsEntityService;
import ru.sofitlabs.firstwebapp.data.user.UserEntity;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private UserEntityService userEntityService;


    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(name = "login") final String login, @RequestParam(name = "password") final String password) {
        if (login == null || "".equals(login)) {
            return new ModelAndView("emptyLogin");
        } else if (!userEntityService.getAllByLogin(login).isEmpty()) {
            return new ModelAndView("loginExists");
        } else {
            final UserEntity userEntity = new UserEntity();
            userEntity.setLogin(login);
            userEntity.setPassword(password);
            userEntityService.add(userEntity);
            return new ModelAndView("authorisation");
        }
    }
    @RequestMapping(value = "authorisation",method = RequestMethod.POST)
    public ModelAndView auth(@RequestParam(name = "login") final String login, @RequestParam(name = "password") final String password){
        if (userEntityService.getAllByLogin(login).isEmpty()){
            return new ModelAndView("loginNotExists");
        } else return new ModelAndView("userOptions");
    }

    @RequestMapping(value = {"authorisation", ""}, method = GET)
    public ModelAndView viewAuthorisation() {
        return new ModelAndView("authorisation");
    }

    @RequestMapping(value = "registration", method = GET)
    public ModelAndView viewRegistration() {
        return new ModelAndView("registration");
    }


    @RequestMapping(value = "users", method = GET)
    public ModelAndView viewAllUsers() {
        return new ModelAndView("data");
    }


    @RequestMapping(value = "view", method = GET)
    @ResponseBody
    public String getAll() {
        return userEntityService.getAll().toString();
    }





}
