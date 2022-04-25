package com.riskers.githubx.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author riskers
 */
@Document(collation = "star")
@Data
public class Star {
    @MongoId
    public String id;

    public String htmlUrl;

    public String fullName;

    public Integer groupId;

    public Integer createTime;

    public Integer updateTime;
}
