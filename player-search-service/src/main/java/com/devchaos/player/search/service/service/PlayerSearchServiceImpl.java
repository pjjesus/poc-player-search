package com.devchaos.player.search.service.service;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.domain.AuthorizedQueryParam;
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
    public List<Player> search(MultiValueMap<String, String> queryParams) {
        AuthorizedQueryParam.allParamsAreValid(queryParams.keySet());

        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        queryParams.entrySet().forEach(e -> boolQueryBuilder.must(AuthorizedQueryParam.getValue(e)));

        return Lists.newArrayList(esPlayersRepository.search(boolQueryBuilder));
    }
}
