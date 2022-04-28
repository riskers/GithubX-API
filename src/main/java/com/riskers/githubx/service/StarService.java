package com.riskers.githubx.service;

import com.riskers.githubx.entity.Star;

import java.util.List;

/**
 * @author riskers
 */
public interface StarService {
    public Integer saveAll(List<Star> star);

    public Boolean clearAll();

    public List<Star> search(Integer groupId, Integer tagId, String fullName);
}
