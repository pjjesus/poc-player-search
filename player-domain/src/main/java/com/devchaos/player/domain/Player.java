package com.devchaos.player.domain;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

/**
 * @author Paulo Jesus
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = Player.COLLECTION_NAME)
@org.springframework.data.elasticsearch.annotations.Document(indexName = Player.COLLECTION_NAME, type = "player")
public class Player {
    public static final String COLLECTION_NAME = "players";

    @Id
    private String id;
    @org.springframework.data.mongodb.core.mapping.Field("particulars.firstname")
    private String firstName;
    @org.springframework.data.mongodb.core.mapping.Field("particulars.lastname")
    private String lastName;

    public String getId() {
        return id;
    }

    public Player setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstname", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
