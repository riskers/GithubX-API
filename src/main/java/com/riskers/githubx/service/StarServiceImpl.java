package com.riskers.githubx.service;

import com.mongodb.bulk.BulkWriteResult;
import com.riskers.githubx.entity.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author riskers
 */
@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer saveAll(List<Star> stars) {
        BulkWriteResult result = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Star.class).insert(stars).execute();

        return result.getInsertedCount();
    }

    @Override
    public Boolean clearAll() {
        mongoTemplate.dropCollection(Const.STAR_COLLECTION_NAME);
        return true;
    }

    @Override
    public List<Star> findAll() {
        return mongoTemplate.findAll(Star.class, Const.STAR_COLLECTION_NAME);
    }

    @Override
    public List<Star> findByGroupId(Integer groupId) {
        AggregationOperation match = Aggregation.match(Criteria.where("groupId").is(groupId));

        return getStars(match);
    }

    @Override
    public List<Star> findByTagId(Integer tagId) {
        AggregationOperation match = Aggregation.match(Criteria.where("tagId").is(tagId));

        return getStars(match);
    }

    @Override
    public List<Star> findByFullName(String fullName) {
        Pattern pattern = Pattern.compile(fullName, Pattern.CASE_INSENSITIVE);
        AggregationOperation match = Aggregation.match(Criteria.where("fullName").regex(pattern));

        return getStars(match);
    }

    private List<Star> getStars(AggregationOperation match) {
        LookupOperation lookupGroupOperation = LookupOperation.newLookup().from(Const.GROUPS_COLLECTION_NAME).localField("groupId").foreignField("id").as("group");
        LookupOperation lookupTagOperation = LookupOperation.newLookup().from(Const.TAGS_COLLECTION_NAME).localField("tagId").foreignField("id").as("tag");
        Aggregation aggregation = Aggregation.newAggregation(match, lookupGroupOperation, lookupTagOperation);
        AggregationResults<Star> aggregate = mongoTemplate.aggregate(aggregation, Const.STAR_COLLECTION_NAME, Star.class);

        return aggregate.getMappedResults();
    }
}
