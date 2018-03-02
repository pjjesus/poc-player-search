package com.devchaos.player.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @author Paulo Jesus
 */
@Data
@org.springframework.data.mongodb.core.mapping.Document(collection = Player.COLLECTION_NAME)
@org.springframework.data.elasticsearch.annotations.Document(indexName = Player.COLLECTION_NAME, type = "player")
@Setting(settingPath = "es-lowercase-analyzer.json")
public class Player {
    public static final String COLLECTION_NAME = "players";

    @Id
    private String id;


    @MultiField(mainField = @Field(type = FieldType.text, store = true),
            otherFields = {
                    @InnerField(suffix = "lowercase", type = FieldType.text, indexAnalyzer = "lowercasean", searchAnalyzer = "standard")
            }
    )
    @org.springframework.data.mongodb.core.mapping.Field("particulars.firstname")
    private String firstName;

    @MultiField(mainField = @Field(type = FieldType.text, store = true),
            otherFields = {
                    @InnerField(suffix = "lowercase", type = FieldType.text, indexAnalyzer = "lowercasean", searchAnalyzer = "standard")
            }
    )
    @org.springframework.data.mongodb.core.mapping.Field("particulars.lastname")
    private String lastName;
}
