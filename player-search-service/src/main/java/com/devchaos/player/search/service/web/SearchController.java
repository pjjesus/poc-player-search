package com.devchaos.player.search.service.web;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.service.PlayerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private PlayerSearchService playerSearchService;

    @GetMapping("/search")
    public List<Player> search(@RequestParam MultiValueMap<String, String> parameters) {
        return playerSearchService.search(parameters);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity handleWrongRequest(HttpServletRequest req, Exception ex) {
        LOGGER.error("Invalid search parameters", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(HttpServletRequest req, Exception ex) {
        LOGGER.error("Search could not be completed", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


}
