package com.devchaos.player.service.web;

import com.devchaos.player.service.service.PlayerDispatcher;
import com.devchaos.player.domain.PlayerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@RestController
public class PlayerEventNotificationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerEventNotificationController.class);

    @Autowired
    private PlayerDispatcher playerDispatcher;

    @GetMapping("/player/notification")
    public boolean sendMessage(PlayerEvent playerEvent) throws ExecutionException, InterruptedException {
        return this.playerDispatcher.dispatch(playerEvent);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(HttpServletRequest req, Exception ex) {
        LOGGER.error("Error Dispatching Player Event Notification", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
