package com.devchaos.player.service.web;

import com.devchaos.player.domain.Player;
import com.devchaos.player.domain.PlayerNotFoundException;
import com.devchaos.player.service.repositories.mongo.PlayerRepository;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    BeanUtilsBean playerBeanUtils;

    @GetMapping
    public List<Player> all() {
        return playerRepository.findAll();
    }

    @GetMapping("{id}")
    public Player get(@PathVariable String id) throws PlayerNotFoundException {
        return playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    }

    @PostMapping
    public Player create(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @PutMapping("{id}")
    public Player update(@PathVariable String id, @RequestBody Player newData) throws PlayerNotFoundException, InvocationTargetException, IllegalAccessException {
        Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        playerBeanUtils.copyProperties(player, newData);
        return playerRepository.save(player);
    }

    @ExceptionHandler({PlayerNotFoundException.class})
    public ResponseEntity handleNotFoundException(HttpServletRequest req) {
        LOGGER.error("Player Not Found: {}", req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(HttpServletRequest req, Exception ex) {
        LOGGER.error("Error while processing request {}{}", req.getRequestURI(), req.getQueryString(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
