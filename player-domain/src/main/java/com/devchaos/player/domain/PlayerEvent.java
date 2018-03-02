package com.devchaos.player.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

/**
 * @author Paulo Jesus
 */
public class PlayerEvent {

    private String playerId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime producedDate;

    public PlayerEvent() {
        this.producedDate = LocalDateTime.now();
    }

    public String getPlayerId() {
        return playerId;
    }

    public PlayerEvent setPlayerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    public LocalDateTime getProducedDate() {
        return producedDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("playerId", playerId)
                .add("producedDate", producedDate)
                .toString();
    }
}
