package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("dev.paie.service, dev.paie.util")
@ImportResource("classpath:jdd-config.xml")
public class ServicesConfig {

}
