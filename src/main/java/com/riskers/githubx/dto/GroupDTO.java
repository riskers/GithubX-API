package com.riskers.githubx.dto;

import com.riskers.githubx.entity.Group;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author riskers
 */
@Data
public class GroupDTO extends Group {
    public Long starCount;
    public Long gistCount;

    @Builder
    public GroupDTO(int id, String name, long starCount, long gistCount) {
        super(id, name);
        this.starCount = starCount;
        this.gistCount = gistCount;
    }
}
