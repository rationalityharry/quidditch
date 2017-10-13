package ru.sofitlabs.firstwebapp.utils;

import javax.servlet.http.HttpServletRequest;

public class Utils {
    public static boolean isAuthorised(HttpServletRequest request) {
        return !(request.getSession().getAttribute("user") == null);

    }
}
