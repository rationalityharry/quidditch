package ru.sofitlabs.firstwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sofitlabs.firstwebapp.data.user.UserEntity;
import ru.sofitlabs.firstwebapp.data.user.UserEntityService;
import ru.sofitlabs.firstwebapp.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@SessionAttributes(value = "user")
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private UserEntityService userEntityService;


    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public void add(@RequestParam(name = "login") final String login,
                    @RequestParam(name = "password") final String password) {
        if (!userEntityService.getAllByLogin(login).isEmpty()) {

        } else {
            final UserEntity userEntity = new UserEntity();
            userEntity.setLogin(login);
            userEntity.setPassword(password);
            userEntityService.add(userEntity);
        }
    }


    @RequestMapping(value = "authorisation", method = RequestMethod.POST)
    public UserEntity auth(@RequestParam(name = "login") final String login,
                           @RequestParam(name = "password") final String password) {
        final UserEntity user = userEntityService.getOneByLogin(login);
        if (user==null || !user.getPassword().equals(password)) {
            return null;
        } else {
            return user;
        }
    }

    @RequestMapping(value = {"authorisation", ""}, method = GET)
    public ModelAndView viewAuthorisation() {
        return new ModelAndView("main");
    }

    @RequestMapping(value = "registration", method = GET)
    public ModelAndView viewRegistration() {
        return new ModelAndView("registration");
    }


    @RequestMapping(value = "users", method = GET)
    public ModelAndView viewAllUsers(HttpServletRequest request) {

        if (Utils.isAuthorised(request)) {
            return new ModelAndView("data");
        } else {
            return new ModelAndView("noAuthorisation");
        }
    }


    @RequestMapping(value = "view", method = GET)
    @ResponseBody
    public String getAll() {
        return userEntityService.getAll().toString();
    }


}
