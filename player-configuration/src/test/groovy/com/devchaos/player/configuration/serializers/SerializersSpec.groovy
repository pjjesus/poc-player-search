package com.devchaos.player.configuration.serializers

import com.devchaos.player.domain.Player
import com.devchaos.player.domain.PlayerEvent
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

import static junit.framework.TestCase.assertTrue

/**
 * @author Paulo Jesus
 */
class SerializersSpec extends Specification {
    Serializers serializers = new Serializers();

    @Unroll
    def "when copying two objects, should only copy non null properties #run"() {
        when: "there are two objects"
        def playerOrigin = Player.builder().id(origId).firstName(origFirstName).lastName(origLastName).build()
        def playerDest = Player.builder().id(destId).firstName(destFirstName).lastName(destLastName).build()

        and: "and origin object is copied into destiny object"
        serializers.playerBeanUtils().copyProperties(playerDest, playerOrigin)

        then: "first condition"
        if (1 == run) {
            assertTrue "equals", playerDest.id == origId
            assertTrue "equals", playerDest.firstName == origFirstName
            assertTrue "equals", playerDest.lastName == origLastName
        }

        and: "second condition"
        if (2 == run) {
            assertTrue "equals", playerDest.id == origId
            assertTrue "equals", playerDest.firstName == destFirstName
            assertTrue "equals", playerDest.lastName == origLastName
        }

        and: "second condition"
        if (3 == run) {
            assertTrue "equals", playerDest.id == destId
            assertTrue "equals", playerDest.firstName == null
            assertTrue "equals", playerDest.lastName == destLastName
        }

        where:
        run | origId | origFirstName | origLastName | destId     | destFirstName | destLastName
        1   | "ID"   | "FIRST-NAME"  | "LAST-NAME"  | null       | "FIXED-FIRST" | "FIXED-LAST"
        2   | "ID"   | null          | "LAST-NAME"  | "FIXED-ID" | "FIXED-FIRST" | "FIXED-LAST"
        3   | null   | null          | null         | "FIXED-ID" | null          | "FIXED-LAST"
    }

    def "ObjectMapper serializes LocalDateTime"() {
        given: "the object mapper"
        ObjectMapper objectMapper = serializers.objectMapper()

        and: "a player event"
        def now = LocalDateTime.now()
        def playerEvent = new PlayerEvent("ID");
        playerEvent.producedDate = now

        and: "a Player Event is serialized"
        String serialized = objectMapper.writeValueAsString(playerEvent)

        when: "it gets deserialized"
        PlayerEvent deserialized = objectMapper.readValue(serialized, PlayerEvent.class)

        then: "producedDate is the correct one together with the playerId"
        deserialized.producedDate == now
        deserialized.playerId == "ID"
    }
}