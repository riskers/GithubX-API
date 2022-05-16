package com.riskers.githubx.entity;

import lombok.Data;

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
    public List<String> tagsId;

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
