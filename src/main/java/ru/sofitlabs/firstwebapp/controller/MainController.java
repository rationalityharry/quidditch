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

    @RequestMapping(value = "authorisation", method = RequestMethod.POST)
    public ModelAndView auth(final HttpServletRequest request,
                             @RequestParam(name = "login") final String login,
                             @RequestParam(name = "password") final String password) {
        final List<UserEntity> allByLogin = userEntityService.getAllByLogin(login);
        if (allByLogin.isEmpty()) {
            return new ModelAndView("loginNotExists");
        }
        final UserEntity userEntity = allByLogin.get(0);
        if (!userEntity.getPassword().equals(password)) {
            return new ModelAndView("wrongPassword");
        } else {
            request.getSession().setAttribute("user", userEntity);
            return new ModelAndView("user");
        }
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
