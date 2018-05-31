package dev.paie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("dev.paie.service, dev.paie.util")
<<<<<<< HEAD
@Import({ JpaConfig.class, HerokuDBConfig.class })
=======
@Import({ JpaConfig.class, DataSourceMySQLConfig.class, SecurityConfig.class })
>>>>>>> master

@EnableJpaRepositories("dev.paie.repository")
public class ServicesConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
