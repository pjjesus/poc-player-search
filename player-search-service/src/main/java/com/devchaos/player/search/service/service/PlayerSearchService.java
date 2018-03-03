package com.devchaos.player.search.service.service;

import com.devchaos.player.domain.Player;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author Paulo Jesus
 */
public interface PlayerSearchService {
    List<Player> search(MultiValueMap<String, String> queryParams);
}
