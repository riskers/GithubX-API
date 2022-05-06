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

        defaultGroup.setId(0);
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
        return mongoTemplate.findById(groupId, Group.class);
    }

    @Override
    public Group addGroup(String name) {
        Group group = new Group();
        group.setName(name);

        return mongoTemplate.save(group);
    }

    @Override
    public Group updateGroup(Long groupId, String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(groupId));

        Group group = mongoTemplate.findOne(query, Group.class);
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
    public List<GroupDTO> getGroupList() {
        List<GroupDTO> res = new ArrayList<>();
        List<Group> groupList = mongoTemplate.findAll(Group.class);

        groupList.forEach(group -> {
            Integer groupId = group.getId();
            long starCount = getStarCount(groupId);
            long gistCount = getGistCount(groupId);

            GroupDTO groupDTO = GroupDTO.builder().id(groupId).name(group.getName()).starCount(starCount).gistCount(gistCount).build();

            res.add(groupDTO);
        });

        return res;
    }

    private long getStarCount(int groupId) {
        Query query = new Query();
        query.addCriteria(new Criteria("groupId").is(groupId));
        return mongoTemplate.count(query, Star.class);
    }

    private long getGistCount(int groupId) {
        Query query = new Query();
        query.addCriteria(new Criteria("groupId").is(groupId));
        return mongoTemplate.count(query, Gist.class);
    }
}
