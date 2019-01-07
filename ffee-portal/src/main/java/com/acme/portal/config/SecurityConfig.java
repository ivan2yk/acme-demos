package com.acme.portal.config;

import com.acme.portal.util.PasswordEncoderUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Ivan on 6/01/2019.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
        simpleAuthorityMapper.setConvertToUpperCase(true);
        simpleAuthorityMapper.setDefaultAuthority("USER");
        return simpleAuthorityMapper;
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderUtil.passwordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .authoritiesMapper(this.authoritiesMapper())
                .contextSource()
                .url("ldap://localhost:10389/dc=frankmoley,dc=com")
                .and()
                .passwordCompare()
                .passwordEncoder(this.encoder())
                .passwordAttribute("userPassword");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/webjars/**").permitAll()
                .antMatchers("/anonymous").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html").permitAll()
                .loginProcessingUrl("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login.html").permitAll();
    }

}