package com.devchaos.player.search.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@PropertySources({
        @PropertySource("classpath:elasticsearch-config.properties")
})
@EnableElasticsearchRepositories(basePackages = {"com.devchaos.player.search.service.repositories.es"})
@SpringBootApplication(scanBasePackages = {"com.devchaos"},
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class,
                MongoRepositoriesAutoConfiguration.class,
                KafkaAutoConfiguration.class})
public class PlayerSearchServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(PlayerSearchServiceApp.class, args);
    }
}
