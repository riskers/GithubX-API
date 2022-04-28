package com.riskers.githubx.service;

import com.mongodb.bulk.BulkWriteResult;
import com.riskers.githubx.entity.Star;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public List<Star> search(Integer groupId, Integer tagId, String fullName) {
        Criteria criteria = new Criteria();

        if (!Objects.isNull(groupId)) {
            criteria = criteria.where("groupId").is(groupId);
        }

        if (!Objects.isNull(tagId)) {
            criteria = criteria.where("tagId").is(tagId);
        }

        if (!Strings.isEmpty(fullName)) {
            Pattern pattern = Pattern.compile(fullName, Pattern.CASE_INSENSITIVE);
            criteria = criteria.where("fullName").regex(pattern);
        }

        return aggregate(criteria);
    }

    private List<Star> aggregate(Criteria criteria) {
        AggregationOperation match = Aggregation.match(criteria);

        LookupOperation lookupGroupOperation = LookupOperation.newLookup().from(Const.GROUPS_COLLECTION_NAME).localField("groupId").foreignField("id").as("group");

        LookupOperation lookupTagOperation = LookupOperation.newLookup().from(Const.TAGS_COLLECTION_NAME).localField("tagsId").foreignField("id").as("tags");

        AggregationOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "updateTime"));

        Aggregation aggregation = Aggregation.newAggregation(match, lookupGroupOperation, lookupTagOperation, sortOperation);

        AggregationResults<Star> aggregate = mongoTemplate.aggregate(aggregation, Const.STAR_COLLECTION_NAME, Star.class);

        return aggregate.getMappedResults();
    }
}
