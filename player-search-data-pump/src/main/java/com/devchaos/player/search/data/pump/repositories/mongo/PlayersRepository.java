package com.devchaos.player.search.data.pump.repositories.mongo;

import com.devchaos.player.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Paulo Jesus
 */
public interface PlayersRepository extends MongoRepository<Player, String> {
}
