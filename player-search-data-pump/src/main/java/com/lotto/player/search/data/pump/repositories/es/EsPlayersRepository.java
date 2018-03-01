package com.lotto.player.search.data.pump.repositories.es;

import com.lotto.player.search.domain.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Paulo Jesus
 */
public interface EsPlayersRepository extends ElasticsearchRepository<Player, String> {
}
