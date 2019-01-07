package com.acme.portal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 6/01/2019.
 */
public class PasswordEncoderUtil {

    private PasswordEncoderUtil() {
    }

    public static PasswordEncoder passwordEncoder() {
        LdapShaPasswordEncoder messageDigestPasswordEncoder = new LdapShaPasswordEncoder();

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        passworEncoder.setDefaultPasswordEncoderForMatches(messageDigestPasswordEncoder);

        return passworEncoder;
    }

}
