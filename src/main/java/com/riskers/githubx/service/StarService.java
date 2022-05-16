package com.riskers.githubx.service;

import com.riskers.githubx.entity.Star;

import java.util.List;

/**
 * @author riskers
 */
public interface StarService {
    public Boolean addStar(Star star);

    public void sjt(String tagId, Long sid);

    public void gjt(String tagId, Long gid);

    public Star getStarInfo(Long id);

    public Boolean deleteStar(Integer id);

    public Integer saveAll(List<Star> star);

    public Boolean clearAll();

    public List<Star> search(Integer groupId, String tagId, String fullName);
}
