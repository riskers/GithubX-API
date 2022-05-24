package com.riskers.githubx.impl;

import com.riskers.githubx.common.Const;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.service.GistService;
import org.apache.logging.log4j.util.Strings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

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

    @Override
    public List<Gist> search(Integer groupId, String tagId, String description) {
        Criteria criteria = new Criteria();

        if (!Objects.isNull(groupId)) {
            criteria = criteria.where("groupId").is(groupId);
        }

        if (!Objects.isNull(tagId)) {
            criteria = criteria.where("tagsId").is(new ObjectId(tagId));
        }

        if (!Strings.isEmpty(description)) {
            Pattern pattern = Pattern.compile(description, Pattern.CASE_INSENSITIVE);
            criteria = criteria.where("description").regex(pattern);
        }

        return getAggregate(criteria);
    }

    @Override
    public boolean addGist(Gist gist) {
        return mongoTemplate.save(gist).getId() != null;
    }

    @Override
    public boolean deleteGist(Integer id) {
        Query query = new Query();
        query.addCriteria(new Criteria("id").is(id));

        return mongoTemplate.remove(query, Gist.class).getDeletedCount() > 0;
    }

    private List<Gist> getAggregate(Criteria criteria) {
        AggregationOperation match = Aggregation.match(criteria);

        LookupOperation lookupGroupOperation = LookupOperation.newLookup().from(Const.GROUPS_COLLECTION_NAME).localField("groupId").foreignField("id").as("group");

        AggregationOperation lookupTagOperation = LookupOperation.newLookup().from(Const.TAGS_COLLECTION_NAME).localField("tagsId").foreignField("_id").as("tags");

        AggregationOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "updateTime"));

        ProjectionOperation projectOperation = Aggregation.project("id", "description", "groupId", "createTime", "updateTime", "htmlUrl", "tagsId", "tags");
        projectOperation = projectOperation.and("group").arrayElementAt(0).as("group");

        Aggregation aggregation = Aggregation.newAggregation(match, lookupGroupOperation, lookupTagOperation, projectOperation, sortOperation);

        AggregationResults<Gist> aggregate = mongoTemplate.aggregate(aggregation, Const.GISTS_COLLECTION_NAME, Gist.class);

        return aggregate.getMappedResults();
    }
}
