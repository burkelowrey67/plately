package com.flatironstudios.plately.security;

import org.springframework.http.ResponseCookie;

public class CookieFactory {


    public static ResponseCookie createCookie(String token) {
        ResponseCookie c = ResponseCookie
            .from("jwt", token)
            .httpOnly(true)
            .path("/")
            .sameSite("Lax")
            .maxAge(86400)
            .build();
        return c;
    }

    public static ResponseCookie createExpiredCookie() {
        ResponseCookie c = ResponseCookie
            .from("jwt", null)
            .httpOnly(true)
            .maxAge(0)
            .path("/")
            .build();
            
        return c;
    }
}
