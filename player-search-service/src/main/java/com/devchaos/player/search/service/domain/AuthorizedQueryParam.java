package com.devchaos.player.search.service.domain;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Paulo Jesus
 */
public enum AuthorizedQueryParam {
    FIRST_NAME("firstName", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0))),
    LAST_NAME("lastName", e -> QueryBuilders.wildcardQuery(e.getKey(), e.getValue().get(0))),
    VERIFIED("verified", e -> QueryBuilders.matchQuery(e.getKey(), Boolean.valueOf(e.getValue().get(0))));

    private String requestParamName;
    private Function<Map.Entry<String, List<String>>, QueryBuilder> valueFunction;
    private static Set<String> authorizedParamNames;

    static {
        authorizedParamNames = Arrays.stream(values())
                .map(AuthorizedQueryParam::getRequestParamName)
                .collect(Collectors.toSet());
    }

    AuthorizedQueryParam(String requestParamName, Function<Map.Entry<String, List<String>>, QueryBuilder> valueFunction) {
        this.requestParamName = requestParamName;
        this.valueFunction = valueFunction;
    }

    public String getRequestParamName() {
        return this.requestParamName;
    }

    public Function<Map.Entry<String, List<String>>, QueryBuilder> getValueFunction() {
        return valueFunction;
    }

    public static void allParamsAreValid(Set<String> requestParams) {
        requestParams.forEach(param -> Assert.isTrue(authorizedParamNames.contains(param),
                String.format("An invalid parameter '%s' was supplied - valid params are: %s", param, authorizedParamNames.toString())));
    }

    public static AuthorizedQueryParam getByRequestParamName(String requestParamName) {
        return Arrays.stream(values())
                .filter(authorizedParam -> authorizedParam.getRequestParamName().equals(requestParamName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static QueryBuilder getValue(Map.Entry<String, List<String>> entry) {
        return getByRequestParamName(entry.getKey()).getValueFunction().apply(entry);
    }
}
