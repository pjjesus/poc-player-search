package com.devchaos.player.search.service.service;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.domain.AuthorizedSearchParameters;
import com.devchaos.player.search.service.repositories.es.EsPlayersRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author Paulo Jesus
 */
@Service
public class PlayerSearchServiceImpl implements PlayerSearchService {

    @Autowired
    EsPlayersRepository esPlayersRepository;

    @Override
    public List<Player> search(final MultiValueMap<String, String> queryParams) throws IllegalArgumentException {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        queryParams.entrySet().forEach(e -> boolQueryBuilder.must(AuthorizedSearchParameters.build(e)));

        return Lists.newArrayList(esPlayersRepository.search(boolQueryBuilder));
    }
}
