package com.devchaos.player.search.service.service;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.domain.QueryItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paulo Jesus
 */
@Service
public interface SearchService {
    List<Player> find(List<QueryItem> queryItems);
}
