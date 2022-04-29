package com.riskers.githubx.service;

import com.riskers.githubx.dto.GroupDTO;
import com.riskers.githubx.entity.Group;

import java.util.List;

public interface GroupService {
    public void resetGroup();

    public Group getGroupInfo(Integer groupId);

    public Group addGroup(String name);

    public Group updateGroup(Integer groupId, String name);

    public long delGroup(Integer groupId);

    public List<GroupDTO> getGroupList();
}
