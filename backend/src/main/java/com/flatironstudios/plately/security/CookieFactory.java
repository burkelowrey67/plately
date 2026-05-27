package com.flatironstudios.plately.security;

import jakarta.servlet.http.Cookie;

public class CookieFactory {


    public static Cookie createCookie(String token) {
        Cookie c = new Cookie("jwt", token);
        c.setHttpOnly(true);
        c.setMaxAge(86400);
        c.setPath("/");
        return c;
    }

    public static Cookie createExpiredCookie() {
        Cookie c = new Cookie("jwt", null);
        c.setHttpOnly(true);
        c.setMaxAge(0);
        c.setPath("/");
        return c;
    }
}
