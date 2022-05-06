package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.dto.TagDTO;
import com.riskers.githubx.entity.Tag;
import com.riskers.githubx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author riskers
 */
@RestController
@RequestMapping(path = "/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public Result<List<TagDTO>> all() {
        List<TagDTO> tagsList = tagService.getTagsList();

        return Result.success(tagsList);
    }

    @PostMapping("/")
    public Result<Tag> addTag(@RequestBody String name) {
        Tag tag = tagService.addTag(name);
        return Result.success(tag);
    }

    @PutMapping("/{id}")
    public Result<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        Tag updatedTag = tagService.updateTag(id, tag.name);
        return Result.success(updatedTag);
    }

    @DeleteMapping("/{id}")
    public Result<Long> deleteTag(@PathVariable Long id) {
        Long count = tagService.deleteTag(id);
        return Result.success(count);
    }
}
