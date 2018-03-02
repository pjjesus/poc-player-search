package com.devchaos.player.search.data.pump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@PropertySources({
        @PropertySource("classpath:kafka-config.properties"),
        @PropertySource("classpath:mongodb-config.properties"),
        @PropertySource("classpath:elasticsearch-config.properties")
})
@EnableMongoRepositories(basePackages = {"com.devchaos.player.search.data.pump.repositories.mongo"})
@EnableElasticsearchRepositories(basePackages = {"com.devchaos.player.search.data.pump.repositories.es"})
@SpringBootApplication(scanBasePackages = {"com.devchaos"})
public class PlayerDataPumpApp {
    public static void main(String[] args) {
        SpringApplication.run(PlayerDataPumpApp.class, args);
    }
}
