package com.lotto.player.search.configuration.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String bootstrap;
    private String playerDataPumpGroup;
    private String playerTopic;

    public String getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap;
    }

    public String getPlayerDataPumpGroup() {
        return playerDataPumpGroup;
    }

    public void setPlayerDataPumpGroup(String playerDataPumpGroup) {
        this.playerDataPumpGroup = playerDataPumpGroup;
    }

    public String getPlayerTopic() {
        return playerTopic;
    }

    public void setPlayerTopic(String playerTopic) {
        this.playerTopic = playerTopic;
    }
}
