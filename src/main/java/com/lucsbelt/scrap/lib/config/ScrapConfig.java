package com.lucsbelt.scrap.lib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lucsbelt.scrap.lib.task.ScrapperTaskImpl;

@Configuration
public class ScrapConfig {
	
	@Bean
	public ScrapperTaskImpl getScrapper() {
		return new ScrapperTaskImpl();
	}

}
