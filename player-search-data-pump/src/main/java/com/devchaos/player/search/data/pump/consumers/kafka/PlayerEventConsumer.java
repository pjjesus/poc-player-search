package com.devchaos.player.search.data.pump.consumers.kafka;

import com.devchaos.player.search.data.pump.repositories.es.EsPlayersRepository;
import com.devchaos.player.search.data.pump.repositories.mongo.PlayersRepository;
import com.devchaos.player.domain.Player;
import com.devchaos.player.domain.PlayerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerEventConsumer {
    private static final Logger log = LoggerFactory.getLogger(PlayerEventConsumer.class);

    @Autowired
    private PlayersRepository playersRepository;
    @Autowired
    private EsPlayersRepository esPlayersRepository;

    @KafkaListener(topicPartitions = {@TopicPartition(topic = "${kafka.playerTopic}", partitions = {"0"})})
    public void onReceiving(PlayerEvent playerEvent,
                            @Header(KafkaHeaders.OFFSET) Integer offset,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        log.info("Processing topic = {}, partition = {}, offset = {}, playerEvent = {}", topic, partition, offset, playerEvent);

        Optional<Player> player = playersRepository.findById(playerEvent.getPlayerId());
        log.info("Player = {}", player);

        player.ifPresent(p -> esPlayersRepository.save(p));
        log.info("Player = {} was indexed", player);
    }
}
