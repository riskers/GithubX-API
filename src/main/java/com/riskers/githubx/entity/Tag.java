package com.riskers.githubx.entity;

import com.riskers.githubx.service.Const;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author riskers
 */
@Data
@Document(Const.TAGS_COLLECTION_NAME)
public class Tag {
    // @Id
    // public String _id;

    @Id
    public String id;

    public String name;

    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
    }
}
