package com.example.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    /*
    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
*/

        @Bean
        public CorsFilter corsFilter()

        {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200","https://professional-practices-system-k7mn97rom-lu27656348.vercel.app"));
            corsConfiguration.setAllowedMethods(Arrays.asList("GET",

                    "POST", "PUT", "DELETE"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            if (corsConfiguration.getAllowedOrigins().contains("*")) {
                // Only add CORS headers if allowed origins are not restricted
                corsConfiguration.addAllowedOrigin("*");
                corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin");
                corsConfiguration.addAllowedHeader("Access-Control-Allow-Methods");
                corsConfiguration.addAllowedHeader("Access-Control-Allow-Headers");
            }
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);


            return

                    new CorsFilter(source);
        }



}
