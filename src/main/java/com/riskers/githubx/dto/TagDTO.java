package com.riskers.githubx.dto;

import com.riskers.githubx.entity.Tag;
import lombok.Builder;
import lombok.Data;

/**
 * @author riskers
 */
@Data
public class TagDTO extends Tag {
    public Long starCount;
    public Long gistCount;

    @Builder
    public TagDTO(int id, String name, Long starCount, Long gistCount) {
        super(id, name);
        this.starCount = starCount;
        this.gistCount = gistCount;
    }
}
