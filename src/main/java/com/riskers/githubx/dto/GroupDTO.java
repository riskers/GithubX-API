package com.riskers.githubx.dto;

import com.riskers.githubx.entity.Group;
import com.riskers.githubx.entity.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    public List<Group> group;

    /**
     * tag
     */
    public List<Tag> tags;

    @Builder
    public GroupDTO(int id, String name, long starCount, long gistCount) {
        super(id, name);
        this.starCount = starCount;
        this.gistCount = gistCount;
    }
}
