package com.riskers.githubx.service;

import com.riskers.githubx.entity.Gist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author riskers
 */
@Service
public class GistServiceImpl implements GistService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Gist getGist(Long id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Gist.class);
    }
}
