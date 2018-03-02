package com.devchaos.player.search.data.pump.repositories.es;

import com.devchaos.player.domain.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Paulo Jesus
 */
public interface EsPlayersRepository extends ElasticsearchRepository<Player, String> {
}
