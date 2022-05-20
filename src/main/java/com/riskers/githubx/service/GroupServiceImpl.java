package com.riskers.githubx.service;

import com.riskers.githubx.dto.GroupDTO;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.entity.Group;
import com.riskers.githubx.entity.Star;
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
public class GroupServiceImpl implements GroupService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Group getDefaultGroup() {
        Group defaultGroup = new Group();

        defaultGroup.setId(0L);
        defaultGroup.setName("UNGROUP");

        return defaultGroup;
    }

    @Override
    public void resetGroup() {
        mongoTemplate.dropCollection(Const.GROUPS_COLLECTION_NAME);
        mongoTemplate.save(getDefaultGroup());
    }

    @Override
    public Group getGroupInfo(Long groupId) {
        Query query = new Query();
        query.addCriteria(new Criteria("id").is(groupId));

        return mongoTemplate.findOne(query, Group.class);
    }

    private Long getNextGroupId() {
        return (long) (mongoTemplate.findAll(Group.class).size() + 1);
    }

    @Override
    public Group addGroup(Group group) {
        group.setId(getNextGroupId());
        return mongoTemplate.save(group);
    }

    @Override
    public Group updateGroup(Long groupId, String name) {
        Group group = getGroupInfo(groupId);

        if (group != null) {
            group.setName(name);
            mongoTemplate.save(group);
        }

        return group;
    }

    @Override
    public long delGroup(Long groupId) {
        Query query = new Query();
        query.addCriteria(new Criteria("id").is(groupId));

        return mongoTemplate.remove(query, Group.class).getDeletedCount();
    }

    @Override
    public Boolean clearGroup() {
        mongoTemplate.dropCollection(Const.GROUPS_COLLECTION_NAME);
        return true;
    }

    @Override
    public List<GroupDTO> getGroupList() {
        List<GroupDTO> res = new ArrayList<>();
        List<Group> groupList = mongoTemplate.findAll(Group.class);

        groupList.forEach(group -> {
            Long groupId = group.getId();
            long starCount = getStarCount(groupId);
            long gistCount = getGistCount(groupId);

            GroupDTO groupDTO = GroupDTO.builder()._id(group.get_id()).id(groupId).name(group.getName()).starCount(starCount).gistCount(gistCount).build();

            res.add(groupDTO);
        });

        return res;
    }

    private long getStarCount(Long groupId) {
        Query query = new Query();
        query.addCriteria(new Criteria("groupId").is(groupId));
        return mongoTemplate.count(query, Star.class);
    }

    private long getGistCount(Long groupId) {
        Query query = new Query();
        query.addCriteria(new Criteria("groupId").is(groupId));
        return mongoTemplate.count(query, Gist.class);
    }
}
