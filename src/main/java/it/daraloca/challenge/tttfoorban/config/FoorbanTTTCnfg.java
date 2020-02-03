package it.daraloca.challenge.tttfoorban.config;

import java.util.Arrays;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * FoorbanTTTCnfg
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "it.daraloca.challenge.tttfoorban.service", "it.daraloca.challenge.tttfoorban.web" })
public class FoorbanTTTCnfg extends WebSecurityConfigurerAdapter {

    @Value("${api.cors.alloworigin:*}")
    private String allowOrigin;

    @Bean
    public Mapper mapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(new BeanMappingBuilder() {

            @Override
            protected void configure() {
                mapping(UUID.class, UUID.class, TypeMappingOptions.oneWay(),
                        TypeMappingOptions.beanFactory(UUIDBeanFactory.class.getName()));
            }
        });
        return dozerBeanMapper;
    }

    @Bean // Override dell'inizializzazione di questa istanza (Bean)
    public CorsConfigurationSource corsConfigurationSource() { // gli definiamo il tipo CorsConfigurationSource
        final CorsConfiguration configuration = new CorsConfiguration(); // non puù modificabile
        configuration.setAllowedOrigins(Arrays.asList(allowOrigin));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        // for option preflight request
        configuration.setAllowCredentials(true);
        configuration
                .setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "x-auth-token"));
        configuration
                .setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "x-auth-token"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
    }

}