package com.riskers.githubx.service;

import com.riskers.githubx.dto.TagDTO;
import com.riskers.githubx.entity.Tag;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author riskers
 */
public interface TagService {
    public void resetTag();

    public Tag addTag(String name);

    public Tag updateTag(long tagId, String name);

    public Long deleteTag(String tagId);

    public List<TagDTO> getTagsList(Query query);

    public Boolean clear();
}
