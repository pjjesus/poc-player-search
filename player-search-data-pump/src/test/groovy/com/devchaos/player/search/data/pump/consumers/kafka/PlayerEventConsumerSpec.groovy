package com.devchaos.player.search.data.pump.consumers.kafka

import com.devchaos.player.domain.Player
import com.devchaos.player.domain.PlayerEvent
import com.devchaos.player.search.data.pump.repositories.es.EsPlayersRepository
import com.devchaos.player.search.data.pump.repositories.mongo.PlayersRepository
import spock.lang.Specification

/**
 * @author Paulo Jesus
 */
class PlayerEventConsumerSpec extends Specification {

    PlayersRepository playersRepository = Mock()
    EsPlayersRepository esPlayersRepository = Mock()
    PlayerEventConsumer playerEventConsumer

    def setup() {
        playerEventConsumer = new PlayerEventConsumer()

        playerEventConsumer.playersRepository = playersRepository
        playerEventConsumer.esPlayersRepository = esPlayersRepository
    }

    def "will index player if exists"() {
        given: "the player exists in mongo"
        playersRepository.findById(_) >> Optional.ofNullable(Player.builder().build())

        when: "a player event is received"
        playerEventConsumer.onReceiving(new PlayerEvent("someid"), 0, 0, "topic")

        then: "player is index"
        1 * esPlayersRepository.save(_)

    }

    def "will not try to index player if it does not exists"() {
        given: "the player exists in mongo"
        playersRepository.findById(_) >> Optional.ofNullable(null)

        when: "a player event is received"
        playerEventConsumer.onReceiving(new PlayerEvent("someid"), 0, 0, "topic")

        then: "player is not indexed"
        0 * esPlayersRepository.save(_)
    }
}