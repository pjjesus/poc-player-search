package com.devchaos.player.service.aspects;

import com.devchaos.player.domain.Player;
import com.devchaos.player.domain.PlayerEvent;
import com.devchaos.player.service.service.PlayerDispatcher;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

/**
 * @author Paulo Jesus
 */
@Aspect
@Configuration
public class PlayerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerAspect.class);

    @Autowired
    PlayerDispatcher playerDispatcher;

    @After("execution(* com.devchaos.player.service.repositories.mongo.PlayerRepository.save(..)) && args(player,..)")
    public void notifyOnPlayerSave(Player player) throws ExecutionException, InterruptedException {
        LOGGER.info("Saved Player: {}", player);
        playerDispatcher.dispatch(new PlayerEvent(player.getId()));
    }
}
