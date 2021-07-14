package com.figueiras.photocontest.backend.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new JwtFilter(authenticationManager(), jwtGenerator))
                .authorizeRequests()
                .antMatchers("/catalogo-concursos/concursos").permitAll()
                .antMatchers("/catalogo-concursos/categorias").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*").permitAll()
                .antMatchers("/catalogo-usuarios/registrarse").permitAll()
                .antMatchers("/catalogo-usuarios/iniciar-sesion").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/following").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/followers").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/cambio-contrasena").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/dejar-seguir/*").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/seguir/*").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/sigue/*").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/recuperar-contrasena").permitAll()
                .anyRequest().hasRole("USER");

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return source;

    }

}
