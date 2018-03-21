package com.devchaos.player.search.service.service;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.domain.InvalidSearchParamException;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author Paulo Jesus
 */
public interface PlayerSearchService {
    List<Player> search(MultiValueMap<String, String> queryParams) throws InvalidSearchParamException;
}
