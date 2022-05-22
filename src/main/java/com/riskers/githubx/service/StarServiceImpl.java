package com.riskers.githubx.service;

import com.mongodb.bulk.BulkWriteResult;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.entity.Star;
import org.apache.logging.log4j.util.Strings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public Boolean addStar(Star star) {
        return mongoTemplate.save(star).getId() != null;
    }

    @Override
    public Star getStarInfo(Long id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Star.class);
    }

    @Override
    public Boolean deleteStar(Integer id) {
        Query query = new Query();
        query.addCriteria(new Criteria("id").is(id));

        return mongoTemplate.remove(query, Star.class).getDeletedCount() > 0;
    }

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
    public void sjt(String tagId, Long sid) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(sid)), new Update().push("tagsId", tagId), Star.class);
    }

    @Override
    public void gjt(String tagId, Long gid) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(gid)), new Update().push("tagsId", tagId), Gist.class);
    }

    public List<Star> search(Integer groupId, String tagId, String fullName) {
        Criteria criteria = new Criteria();

        if (!Objects.isNull(groupId)) {
            criteria = criteria.where("groupId").is(groupId);
        }

        if (!Objects.isNull(tagId)) {
            criteria = criteria.where("tagsId").is(new ObjectId(tagId));
        }

        if (!Strings.isEmpty(fullName)) {
            Pattern pattern = Pattern.compile(fullName, Pattern.CASE_INSENSITIVE);
            criteria = criteria.where("fullName").regex(pattern);
        }

        return getAggregate(criteria);
    }

    private List<Star> getAggregate(Criteria criteria) {
        AggregationOperation match = Aggregation.match(criteria);

        LookupOperation lookupGroupOperation = LookupOperation.newLookup().from(Const.GROUPS_COLLECTION_NAME).localField("groupId").foreignField("id").as("group");

        AggregationOperation lookupTagOperation = LookupOperation.newLookup().from(Const.TAGS_COLLECTION_NAME).localField("tagsId").foreignField("_id").as("tags");

        AggregationOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "updateTime"));

        ProjectionOperation projectOperation = Aggregation.project("id", "fullName", "groupId", "createTime", "updateTime", "htmlUrl", "tagsId", "tags");
        projectOperation = projectOperation.and("group").arrayElementAt(0).as("group");

        Aggregation aggregation = Aggregation.newAggregation(match, lookupGroupOperation, lookupTagOperation, projectOperation, sortOperation);

        AggregationResults<Star> aggregate = mongoTemplate.aggregate(aggregation, Const.STAR_COLLECTION_NAME, Star.class);

        return aggregate.getMappedResults();
    }
}
