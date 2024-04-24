package com.semillero.ubuntu.auth;

import com.semillero.ubuntu.repositories.UsuarioRepositorio;
import com.semillero.ubuntu.security.CustomAuthenticationSuccessHandler;
import com.semillero.ubuntu.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    private final UsuarioRepositorio usuarioRepository;

    public SecurityConfig(UsuarioRepositorio usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize
                            //.requestMatchers("/**").permitAll()
                            .requestMatchers(HttpMethod.POST,"/contacto/{idMicroemprendiminto}").permitAll()
                            .requestMatchers(HttpMethod.GET,"/microEmprendimiento/listar").permitAll()
                            .requestMatchers(HttpMethod.GET,"/microEmprendimiento/buscarPorNombre/{nombre}").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/microEmprendimiento/cantidades_por_rubro").permitAll()
                            // .requestMatchers(HttpMethod.GET,"/contacto/all").permitAll()
                            // .requestMatchers(HttpMethod.GET,"/contacto/{id}").permitAll()
                            // .requestMatchers(HttpMethod.GET,"/api/ubuntu/paises").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/api/ubuntu/paises/{id}").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/api/ubuntu/paises/{id}/provincias").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/api/ubuntu/provincias").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/api/ubuntu/provincias/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET,"/publicacion/activas").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/publicacion/{id}").permitAll()
                            .requestMatchers(HttpMethod.PUT,"/publicacion/visualizacion/{id}").permitAll()
                            //.requestMatchers(HttpMethod.GET,"/publicacion/visualizaciones_publicaciones_total").permitAll()
                            .requestMatchers(HttpMethod.GET,"/rubro/listarRubros").permitAll()
                            .requestMatchers("/respuesta/*").permitAll()
                            .requestMatchers("/pregunta/*").permitAll()
                            .requestMatchers("/").hasRole("ADMIN")
                            .anyRequest().authenticated();


                })

                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new CustomAuthenticationSuccessHandler(usuarioRepository))


                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Define el punto de salida para el logout
                        .invalidateHttpSession(true) // Invalida la sesión HTTP después del logout
                        .deleteCookies("JSESSIONID") // Elimina las cookies después del logout
                        .logoutSuccessUrl("http://localhost:5173/") // URL de redirección después del logout
                )
                .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
