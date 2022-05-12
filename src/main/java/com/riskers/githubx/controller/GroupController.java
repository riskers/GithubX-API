package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.dto.GroupDTO;
import com.riskers.githubx.entity.Group;
import com.riskers.githubx.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author riskers
 */
@RestController
@RequestMapping(path = "/api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping
    public Result<List<GroupDTO>> getGroupList() {
        List<GroupDTO> groupList = groupService.getGroupList();

        return Result.success(groupList);
    }

    @GetMapping("/{id}")
    public Result<Group> getGroupInfo(@PathVariable long id) {
        Group groupInfo = groupService.getGroupInfo(id);
        return Result.success(groupInfo);
    }

    @PostMapping
    public Result<Group> addGroup(@RequestBody Group group) {
        Group g = groupService.addGroup(group);
        return Result.success(g);
    }

    @PutMapping("/{id}")
    public Result<Group> updateGroup(@PathVariable long id, @RequestBody Group group) {
        Group updateGroup = groupService.updateGroup(id, group.name);

        return Result.success(updateGroup);
    }

    @DeleteMapping
    public Result<Boolean> clearGroup() {
        Boolean res = groupService.clearGroup();
        return Result.success(res);
    }

    @DeleteMapping("/{id}")
    public Result<Long> deleteGroup(@PathVariable long id) {
        long count = groupService.delGroup(id);
        return Result.success(count);
    }
}