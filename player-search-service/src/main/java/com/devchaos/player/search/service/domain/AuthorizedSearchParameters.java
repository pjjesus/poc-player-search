package com.devchaos.player.search.service.domain;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static void validate(Set<String> requestParams) {
        requestParams.forEach(param -> Assert.isTrue(authorized.keySet().contains(param),
                String.format("An invalid parameter '%s' was supplied - valid params are: %s", param, authorized.keySet())));
    }

    public static QueryBuilder build(Map.Entry<String, List<String>> entry) {
        return authorized.get(entry.getKey()).apply(entry);
    }
}