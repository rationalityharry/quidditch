package ru.sofitlabs.firstwebapp.utils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorisationInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (Utils.isAuthorised(request)) {
            return true;
        } else {
            response.sendRedirect("/authorisation");
            return false;
        }
    }

}
