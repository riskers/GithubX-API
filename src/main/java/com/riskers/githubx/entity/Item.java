package com.riskers.githubx.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

/**
 * @author riskers
 */
@Data
public class Item {
    /**
     * repo groupId
     */
    public Integer groupId;

    /**
     * tagsId
     */
    @Field(targetType = FieldType.OBJECT_ID)
    public List<ObjectId> tagsId;

    /**
     * createTime timestamp
     */
    public Long createTime;

    /**
     * updateTime timestamp
     */
    public Long updateTime;

    /**
     * repo html
     * ps. https://githuh.com/riskers/githubx-api
     */
    public String htmlUrl;

    public Group group;

    public List<Tag> tags;
}
