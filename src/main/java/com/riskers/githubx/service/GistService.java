package com.riskers.githubx.service;

import com.riskers.githubx.entity.Gist;

import java.util.List;

/**
 * @author riskers
 */
public interface GistService {
    public Gist getGist(Long id);

    List<Gist> search(Integer groupId, String tagId, String description);

    boolean addGist(Gist gist);

    boolean deleteGist(Integer id);
}
