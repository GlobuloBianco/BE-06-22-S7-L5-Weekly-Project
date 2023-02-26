package it.S7L5WP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.S7L5WP.models.Sonda;

@Configuration
public class Beans {
	@Bean
	@Scope("prototype")
	public Sonda sonda(double latitudine, double longitudine, int smokelevel) {
		return Sonda.builder()
					.latitudine(latitudine)
					.longitudine(longitudine)
					.livelloFumo(smokelevel)
					.build();
	}
}
