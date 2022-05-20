package com.riskers.githubx.dto;

import com.riskers.githubx.entity.Group;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author riskers
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupDTO extends Group {
    public Long starCount;
    public Long gistCount;

    /**
     * group
     */
    // public List<Group> group;

    /**
     * tag
     */
    // public List<Tag> tags;

    @Builder
    public GroupDTO(String _id, Long id, String name, long starCount, long gistCount) {
        super(_id, id, name);
        this.starCount = starCount;
        this.gistCount = gistCount;
    }
}
