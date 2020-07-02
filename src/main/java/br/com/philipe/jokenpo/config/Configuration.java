package br.com.philipe.jokenpo.config;

import org.springframework.context.annotation.Bean;

import br.com.philipe.jokenpo.manager.PlayerManager;
import br.com.philipe.jokenpo.manager.RefereeManager;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean
	public RefereeManager refereeManager() {
		return new RefereeManager();
	}

	@Bean
	public PlayerManager playerManager() {
		return new PlayerManager();
	}

}