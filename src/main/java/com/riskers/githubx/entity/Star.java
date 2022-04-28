package com.riskers.githubx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author riskers
 */

@Data
@Document("stars")
public class Star {
    @Id
    public String _id;

    /**
     * repo html
     * ps. https://githuh.com/riskers/githubx-api
     */
    public String htmlUrl;

    /**
     * repo fullName
     * ps. riskers/github-api
     */
    public String fullName;

    /**
     * repo groupId
     */
    public Integer groupId;

    /**
     * repo tagsId
     */
    public List<Integer> tagsId;

    /**
     * createTime timestamp
     */
    public Long createTime;

    /**
     * updateTime timestamp
     */
    public Long updateTime;

    /**
     * repo group
     */
    public List<Group> group;

    /**
     * repo tag
     */
    public List<Tag> tags;
}
