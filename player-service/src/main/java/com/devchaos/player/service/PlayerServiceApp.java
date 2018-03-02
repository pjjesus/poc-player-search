package com.devchaos.player.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@PropertySources({
        @PropertySource("classpath:kafka-config.properties"),
        @PropertySource("classpath:mongodb-config.properties")})
@EnableMongoRepositories(basePackages = {"com.devchaos.player.service.repositories.mongo"})
@SpringBootApplication(scanBasePackages = {"com.devchaos"},
        exclude = {ElasticsearchAutoConfiguration.class,
                ElasticsearchDataAutoConfiguration.class,
                ElasticsearchRepositoriesAutoConfiguration.class})
public class PlayerServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApp.class, args);
    }

}
