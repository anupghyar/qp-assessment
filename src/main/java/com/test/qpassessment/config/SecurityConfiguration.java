package com.test.qpassessment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfiguration {

    private CustomAuthenticationProvider authProvider;

    public SecurityConfiguration(CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] patterns = {"admin/v1/grocery"};
        String[] anyAuthorities = {"ADMIN", "USER"};
        http
            .authorizeHttpRequests((authz) -> authz.requestMatchers(HttpMethod.GET, patterns)
                    .hasAnyAuthority(anyAuthorities)
                    .requestMatchers(new AntPathRequestMatcher("admin/**"))
                    .hasAuthority("ADMIN")
                    .requestMatchers(new AntPathRequestMatcher("v1/orders"))
                    .hasAnyAuthority(anyAuthorities)
                    .anyRequest().authenticated()
            )
            .httpBasic().and().csrf().disable();
        return http.build();
    }

}