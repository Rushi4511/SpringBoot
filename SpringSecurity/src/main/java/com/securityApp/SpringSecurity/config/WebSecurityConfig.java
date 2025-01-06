package com.securityApp.SpringSecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        /*httpSecurity
                .formLogin(Customizer.withDefaults())
                .formLogin(formLoginConfig ->formLoginConfig.loginPage("/login.html"));

         */


        /*httpSecurity
                .authorizeHttpRequests(auth->auth
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults());


         */



        /*httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/posts").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

         */

        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/posts/**","/auth/**").permitAll()
                        .requestMatchers("/post/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf(csrfConfig->csrfConfig.disable())
                    .sessionManagement(sessionConfig ->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                //.formLogin(Customizer.withDefaults());



        return httpSecurity.build();

    }

    /*
    @Bean
    UserDetailsService myInMemoryUserDetailsService(){

        UserDetails normalUser = User
                .withUsername("rushi")
                .password(passwordEncoder().encode("rushi123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(adminUser,normalUser);



    }*/

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{

        return config.getAuthenticationManager();

    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
