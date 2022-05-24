package com.riskers.githubx.impl;

import com.mongodb.client.result.UpdateResult;
import com.riskers.githubx.entity.Star;
import com.riskers.githubx.service.SjtService;
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
public class SjtServiceImpl implements SjtService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public long delTagInStar(String tagId, Long starId) {
        Query query = Query.query(Criteria.where("id").is(starId));

        Update update = new Update().pull("tagsId", tagId);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Star.class);
        return updateResult.getMatchedCount();
    }
}
