package com.devchaos.player.search.service.service;

import com.devchaos.player.domain.Player;
import com.devchaos.player.search.service.domain.InvalidSearchParamException;
import com.devchaos.player.search.service.domain.SearchParameters;
import com.devchaos.player.search.service.repositories.es.EsPlayersRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * @author Paulo Jesus
 */
@Service
public class PlayerSearchServiceImpl implements PlayerSearchService {

    @Autowired
    private EsPlayersRepository esPlayersRepository;

    @Override
    public List<Player> search(final MultiValueMap<String, String> queryParams) throws InvalidSearchParamException {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (Map.Entry<String, List<String>> param : queryParams.entrySet()) {
            boolQueryBuilder.must(SearchParameters.build(param));
        }

        return Lists.newArrayList(esPlayersRepository.search(boolQueryBuilder));
    }
}
