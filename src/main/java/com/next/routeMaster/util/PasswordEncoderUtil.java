package com.next.routeMaster.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encode(String rawPassword){
        return encoder.encode(rawPassword);
    }
    public boolean matches(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword, encodedPassword);
    }
}
