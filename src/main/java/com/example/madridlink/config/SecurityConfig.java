package com.example.madridlink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitar CSRF para evitar problemas con formularios en desarrollo
                .csrf(csrf -> csrf.disable())

                // 2. Gestión de permisos de rutas
                .authorizeHttpRequests(auth -> auth
                        // Rutas que CUALQUIER persona puede ver sin loguearse
                        .requestMatchers("/", "/login", "/registro", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                        // Cualquier otra ruta requiere que el usuario esté autenticado
                        .anyRequest().authenticated()
                )

                // 3. Configuración del formulario de Login
                .formLogin(form -> form
                        .loginPage("/login")               // Ruta de tu controlador de login
                        .loginProcessingUrl("/login")      // URL donde el HTML envía el POST
                        .defaultSuccessUrl("/", true)      // A dónde va el usuario tras loguearse
                        .failureUrl("/login?error=true")   // A dónde va si falla la clave
                        .permitAll()
                )

                // 4. Configuración del Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // A dónde va al salir
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Este es el motor que encriptará tus contraseñas en la base de datos
        return new BCryptPasswordEncoder();
    }
}