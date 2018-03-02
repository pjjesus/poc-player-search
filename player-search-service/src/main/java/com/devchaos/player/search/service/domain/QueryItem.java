package com.devchaos.player.search.service.domain;

import com.google.common.base.MoreObjects;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paulo Jesus
 */
public class QueryItem {
    private String field;
    private List<Object> values = new ArrayList<>();

    public String getField() {
        return field;
    }

    public QueryItem setField(String field) {
        this.field = field;
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public QueryItem setValues(List<Object> values) {
        this.values = values;
        return this;
    }

    public QueryItem addValue(Object value) {
        values.add(value);
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("field", field)
                .add("values", values)
                .toString();
    }

    public static List<QueryItem> process(MultiValueMap<String, String> searchParams) {
        return searchParams.entrySet().stream()
                .map(e -> QueryItem.build(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public static QueryItem build(String field, List<String> values) {
        QueryItem item = new QueryItem();

        return item;
    }
}
