package com.riskers.githubx.service;

import com.riskers.githubx.dto.TagDTO;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.entity.Star;
import com.riskers.githubx.entity.Tag;
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
    public Tag updateTag(long tagId, String name) {
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
    public Long deleteTag(long tagId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(tagId));

        return mongoTemplate.remove(query, Tag.class).getDeletedCount();
    }

    @Override
    public List<TagDTO> getTagsList() {
        List<TagDTO> res = new ArrayList<>();
        List<Tag> tagsList = mongoTemplate.findAll(Tag.class);

        tagsList.forEach(tag -> {
            Integer tagId = tag.getId();
            long starCount = getStarCount(tagId);
            long gistCount = getGistCount(tagId);

            TagDTO tagDTO = TagDTO.builder().id(tagId).name(tag.getName()).starCount(starCount).gistCount(gistCount).build();

            res.add(tagDTO);
        });

        return res;
    }

    private long getStarCount(int tagId) {
        Query query = new Query();
        query.addCriteria(new Criteria("tagsId").in(tagId));
        return mongoTemplate.count(query, Star.class);
    }

    private long getGistCount(int tagId) {
        Query query = new Query();
        query.addCriteria(new Criteria("tagsId").in(tagId));
        return mongoTemplate.count(query, Gist.class);
    }
}
