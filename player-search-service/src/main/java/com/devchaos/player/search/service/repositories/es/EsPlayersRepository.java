package com.devchaos.player.search.service.repositories.es;

import com.devchaos.player.domain.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Paulo Jesus
 */
public interface EsPlayersRepository extends ElasticsearchRepository<Player, String> {
    List<Player> findByFirstName(String firstName);
}
