package com.riskers.githubx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author riskers
 */

@Data
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
     * createTime timestamp
     */
    public Integer createTime;

    /**
     * updateTime timestamp
     */
    public Integer updateTime;

    /**
     * repo group
     */
    public List<Group> group;

    /**
     * repo tag
     */
    public List<Tag> tag;
}
