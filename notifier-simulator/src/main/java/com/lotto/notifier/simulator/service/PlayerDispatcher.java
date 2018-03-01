package com.lotto.notifier.simulator.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.player.search.domain.PlayerEvent;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PlayerDispatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDispatcher.class);

    @Autowired
    private KafkaTemplate<String, PlayerEvent> playerEventKafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean dispatch(PlayerEvent playerEvent) throws ExecutionException, InterruptedException {
        SendResult<String, PlayerEvent> sendResult = playerEventKafkaTemplate.sendDefault(playerEvent.getPlayerId(), playerEvent).get();
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();

        LOGGER.info("topic = {}, partition = {}, offset = {}, playerEvent = {}",
                recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), playerEvent);

        return true;
    }
}
