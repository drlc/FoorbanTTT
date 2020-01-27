package it.daraloca.challenge.tttfoorban.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * DataCnfg
 */
@Configuration
@EntityScan("it.daraloca.challenge.tttfoorban.data")
@EnableJpaRepositories(basePackages = "it.daraloca.challenge.tttfoorban.data")
public class DataCnfg {

}