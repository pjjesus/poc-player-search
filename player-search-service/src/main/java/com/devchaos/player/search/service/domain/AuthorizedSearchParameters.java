package com.devchaos.player.search.service.domain;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Paulo Jesus
 */
public class AuthorizedSearchParameters {
    private AuthorizedSearchParameters() {
    }

    private static final Map<String, Function<Map.Entry<String, List<String>>, QueryBuilder>> authorized;

    static {
        Map<String, Function<Map.Entry<String, List<String>>, QueryBuilder>> map = new HashMap<>();
        map.put("firstName", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0)));
        map.put("lastName", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0)));
        map.put("verified", e -> QueryBuilders.matchQuery(e.getKey(), Boolean.valueOf(e.getValue().get(0))));

        authorized = Collections.unmodifiableMap(map);
    }

    public static QueryBuilder build(Map.Entry<String, List<String>> entry) throws IllegalArgumentException {
        validate(entry.getKey());
        return authorized.get(entry.getKey()).apply(entry);
    }

    private static void validate(String requestParam) throws IllegalArgumentException {
        Assert.isTrue(authorized.keySet().contains(requestParam),
                String.format("Invalid parameter '%s'. Valid params are: %s", requestParam, authorized.keySet()));
    }
}
