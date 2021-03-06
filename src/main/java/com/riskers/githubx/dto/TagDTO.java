package com.riskers.githubx.dto;

import com.riskers.githubx.entity.Group;
import com.riskers.githubx.entity.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

/**
 * @author riskers
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagDTO extends Tag {
    @Serial
    private static final long serialVersionUID = 6597065769657224059L;

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
    public TagDTO(String _id, String name, Long starCount, Long gistCount) {
        super(_id, name);
        this.starCount = starCount;
        this.gistCount = gistCount;
    }
}
