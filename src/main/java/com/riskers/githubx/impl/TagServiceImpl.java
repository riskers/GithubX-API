package com.riskers.githubx.impl;

import com.riskers.githubx.common.Const;
import com.riskers.githubx.dto.TagDTO;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.entity.Star;
import com.riskers.githubx.entity.Tag;
import com.riskers.githubx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author riskers
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void resetTag() {
        mongoTemplate.dropCollection(Const.TAGS_COLLECTION_NAME);
    }

    @Override
    public Tag addTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);

        mongoTemplate.save(tag);
        return tag;
    }

    @Override
    public Tag updateTag(String tagId, String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(tagId));

        Tag tag = mongoTemplate.findOne(query, Tag.class);
        if (tag != null) {
            tag.setName(name);
            mongoTemplate.save(tag);
        }

        return tag;
    }

    @Override
    public Long deleteTag(String tagId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(tagId));

        return mongoTemplate.remove(query, Tag.class).getDeletedCount();
    }

    @Override
    public List<TagDTO> getTagsList(Query query) {
        List<Tag> tagsList = mongoTemplate.find(query, Tag.class);
        List<TagDTO> res = new ArrayList<>();

        tagsList.forEach(tag -> {
            String tagId = tag.getId();
            long starCount = getStarCount(tagId);
            long gistCount = getGistCount(tagId);

            TagDTO tagDTO = TagDTO.builder()._id(tagId).name(tag.getName()).starCount(starCount).gistCount(gistCount).build();

            res.add(tagDTO);
        });

        return res;
    }

    @Override
    public Boolean clear() {
        mongoTemplate.dropCollection(Const.TAGS_COLLECTION_NAME);
        return true;
    }

    private long getStarCount(String tagId) {
        Query query = new Query();
        query.addCriteria(new Criteria("tagsId").in(tagId));
        return mongoTemplate.count(query, Star.class);
    }

    private long getGistCount(String tagId) {
        Query query = new Query();
        query.addCriteria(new Criteria("tagsId").in(tagId));
        return mongoTemplate.count(query, Gist.class);
    }
}
