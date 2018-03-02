package com.devchaos.player.service.repositories.mongo;

import com.devchaos.player.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Paulo Jesus
 */
public interface PlayerRepository extends MongoRepository<Player, String> {
}
