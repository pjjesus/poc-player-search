package com.devchaos.player.configuration.kafka.producer;

import com.devchaos.player.configuration.kafka.KafkaProperties;
import com.devchaos.player.domain.PlayerEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, PlayerEvent> producerFactory() {
        return new DefaultKafkaProducerFactory(producerConfigs(), stringKeySerializer(), playerEventJsonSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrap());
        return props;
    }

    @Bean
    public KafkaTemplate<String, PlayerEvent> playerEventKafkaTemplate() {
        KafkaTemplate<String, PlayerEvent> kafkaTemplate = new KafkaTemplate(producerFactory());
        kafkaTemplate.setDefaultTopic(kafkaProperties.getPlayerTopic());
        return kafkaTemplate;
    }

    @Bean
    public Serializer stringKeySerializer() {
        return new StringSerializer();
    }

    @Bean
    public Serializer playerEventJsonSerializer() {
        return new JsonSerializer();
    }
}
