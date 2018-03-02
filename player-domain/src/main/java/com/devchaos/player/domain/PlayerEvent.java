package com.devchaos.player.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Paulo Jesus
 */
@Data
public class PlayerEvent {

    private String playerId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime producedDate;

    @SuppressWarnings("unused")
    public PlayerEvent() {
        //for kafka serializer
    }

    public PlayerEvent(String playerId) {
        this.playerId = playerId;
        this.producedDate = LocalDateTime.now();
    }
}
