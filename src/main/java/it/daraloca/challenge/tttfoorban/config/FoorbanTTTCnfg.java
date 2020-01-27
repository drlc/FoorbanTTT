package it.daraloca.challenge.tttfoorban.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * FoorbanTTTCnfg
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "it.daraloca.challenge.tttfoorban.service", "it.daraloca.challenge.tttfoorban.web" })
public class FoorbanTTTCnfg {

}