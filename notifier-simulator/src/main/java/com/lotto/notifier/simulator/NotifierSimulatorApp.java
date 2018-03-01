package com.lotto.notifier.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({@PropertySource("classpath:kafka-config.properties")})
@SpringBootApplication(scanBasePackages = {"com.lotto"},
        exclude = {
                ElasticsearchAutoConfiguration.class,
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class})
public class NotifierSimulatorApp {

    public static void main(String[] args) {
        SpringApplication.run(NotifierSimulatorApp.class, args);
    }

}
