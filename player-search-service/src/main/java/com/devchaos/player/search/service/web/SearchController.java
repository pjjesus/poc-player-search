package com.devchaos.player.search.service.web;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.domain.QueryItem;
import com.devchaos.player.search.service.service.SearchService;
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
import java.util.stream.Collectors;

@RestController
public class SearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public List<String> search(@RequestParam MultiValueMap<String, String> searchParams) {
        return this.searchService.find(QueryItem.process(searchParams)).stream()
                .map(Player::getId)
                .collect(Collectors.toList());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(HttpServletRequest req, Exception ex) {
        LOGGER.error("Search could not be completed", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
