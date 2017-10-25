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

    private static class UserData{
        private String login;
        private String password;

        public UserData() {
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(final String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    @ResponseBody
    public long add(@RequestBody final UserData user) {
        if (!userEntityService.getAllByLogin(user.login).isEmpty()) {
            return 0;
        } else {
            final UserEntity createdUser = new UserEntity();
            createdUser.setLogin(user.login);
            createdUser.setPassword(user.password);
            final UserEntity added = userEntityService.add(createdUser);
            return added.getId();
        }
    }


    @RequestMapping(value = "authorisation", method = RequestMethod.POST)
    @ResponseBody
    public long auth(@RequestBody final UserData userReceived,
                     final HttpServletRequest request) {
        final UserEntity user = userEntityService.getOneByLogin(userReceived.login);
        if (user == null || !user.getPassword().equals(userReceived.password)) {
            return 0;
        } else {
            request.getSession().setAttribute("user", user);
            return user.getId();
        }
    }

    @RequestMapping(value = {"authorisation", ""}, method = GET)
    public ModelAndView viewAuthorisation() {
        return new ModelAndView("main");
    }



}
