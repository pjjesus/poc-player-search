package com.lotto.player.search.data.pump.repositories.mongo;

import com.lotto.player.search.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Paulo Jesus
 */
public interface PlayersRepository extends MongoRepository<Player, String> {
    Optional<Player> findById(String id);
}
