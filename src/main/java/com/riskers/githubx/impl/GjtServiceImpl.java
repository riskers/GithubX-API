package com.riskers.githubx.impl;

import com.mongodb.client.result.UpdateResult;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.service.GjtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * @author riskers
 */
@Service
public class GjtServiceImpl implements GjtService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long delTagInGist(String tagId, Long gistId) {
        Query query = Query.query(Criteria.where("id").is(gistId));

        Update update = new Update().pull("tagsId", tagId);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Gist.class);
        return updateResult.getMatchedCount();
    }
}
