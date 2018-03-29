package com.devchaos.player.configuration.kafka;

import com.devchaos.player.domain.PlayerEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PlayerEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PlayerEvent> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConcurrency(1);
        factory.setConsumerFactory(playerEventConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PlayerEvent> playerEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerProps(), stringKeyDeserializer(), playerEventJsonValueDeserializer());
    }

    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrap());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getPlayerDataPumpGroup());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return props;
    }

    @Bean
    public Deserializer stringKeyDeserializer() {
        return new StringDeserializer();
    }

    @Bean
    public Deserializer playerEventJsonValueDeserializer() {
        return new JsonDeserializer(PlayerEvent.class);
    }
}
