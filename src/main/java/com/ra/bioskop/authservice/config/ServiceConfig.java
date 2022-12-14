package com.ra.bioskop.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.core.io.Resource;

@Configuration
public class ServiceConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * pre-populate database
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean populatorFactoryBean() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[] {
                new ClassPathResource("datajson/role-data.json"),
        });
        return factory;
    }
}
