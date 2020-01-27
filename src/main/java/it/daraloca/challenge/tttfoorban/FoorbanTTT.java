package it.daraloca.challenge.tttfoorban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import it.daraloca.challenge.tttfoorban.config.FoorbanTTTCnfg;

@SpringBootApplication
@ComponentScan(basePackageClasses = { FoorbanTTTCnfg.class })
public class FoorbanTTT {

	public static void main(String[] args) {
		SpringApplication.run(FoorbanTTT.class, args);
	}

}
