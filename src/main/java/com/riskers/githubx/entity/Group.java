package com.riskers.githubx.entity;

import com.riskers.githubx.service.Const;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

/**
 * @author riskers
 */
@Data
@Document(Const.GROUPS_COLLECTION_NAME)
public class Group {

    @Id
    @Field(targetType = FieldType.STRING)
    public String _id;

    public String mm;

    public Long id;

    public String name;

    public Group(String _id, Long id, String name) {
        this._id = _id;
        this.id = id;
        this.name = name;
    }

    public Group() {
    }
}
