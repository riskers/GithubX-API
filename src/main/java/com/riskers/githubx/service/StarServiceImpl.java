package com.riskers.githubx.service;

import com.riskers.githubx.entity.Star;
import com.riskers.githubx.repository.StarRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author riskers
 */
@Service
public class StarServiceImpl implements StarService {

    @Resource
    private StarRepository starRepository;

    @Override
    public Star save(Star star) {
        return starRepository.save(star);
    }
}
