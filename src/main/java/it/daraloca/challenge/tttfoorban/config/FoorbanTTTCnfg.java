package it.daraloca.challenge.tttfoorban.config;

import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * FoorbanTTTCnfg
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "it.daraloca.challenge.tttfoorban.service", "it.daraloca.challenge.tttfoorban.web" })
public class FoorbanTTTCnfg {

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

}