package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.dto.TagDTO;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.entity.Star;
import com.riskers.githubx.entity.Tag;
import com.riskers.githubx.service.GistService;
import com.riskers.githubx.service.StarService;
import com.riskers.githubx.service.TagService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author riskers
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private StarService starService;

    @Autowired
    private GistService gistService;

    @GetMapping
    public Result<List<TagDTO>> all() {
        List<TagDTO> tagsList = tagService.getTagsList(new Query());

        return Result.success(tagsList);
    }

    @PostMapping("/s/{sid}")
    public Result<Tag> addTagWithSid(@PathVariable("sid") Long sid, @RequestBody TagDTO tagDTO) {
        Tag tag = tagService.addTag(tagDTO.getName());

        starService.sjt(tag.getId(), sid);

        return Result.success(tag);
    }

    @PostMapping("/g/{gid}")
    public Result<Tag> addTagWithGid(@PathVariable("gid") Long gid, @RequestBody TagDTO tagDTO) {
        Tag tag = tagService.addTag(tagDTO.getName());

        starService.gjt(tag.getId(), gid);

        return Result.success(tag);
    }

    @GetMapping("/s/{sid}")
    public Result<List<TagDTO>> getTagsInStar(@PathVariable("sid") Long sid) {
        List<TagDTO> tagsList = new ArrayList<>();

        Star starInfo = starService.getStarInfo(sid);

        if (starInfo == null || starInfo.tagsId == null) {
            return Result.success(tagsList);
        }

        List<ObjectId> tagsId = starInfo.tagsId;

        Query query = new Query();
        query.addCriteria(new Criteria("id").in(tagsId));
        tagsList = tagService.getTagsList(query);

        return Result.success(tagsList);
    }

    @GetMapping("/g/{gid}")
    public Result<List<TagDTO>> getTagsInGist(@PathVariable("gid") Long gid) {
        List<TagDTO> tagsList = new ArrayList<>();

        Gist gistInfo = gistService.getGist(gid);

        if (gistInfo == null || gistInfo.tagsId == null) {
            return Result.success(tagsList);
        }

        List<ObjectId> tagsId = gistInfo.tagsId;

        Query query = new Query();
        query.addCriteria(new Criteria("id").in(tagsId));
        tagsList = tagService.getTagsList(query);

        return Result.success(tagsList);
    }

    @PutMapping("/{id}")
    public Result<Tag> updateTag(@PathVariable("id") String id, @RequestBody Tag tag) {
        Tag updatedTag = tagService.updateTag(id, tag.name);
        return Result.success(updatedTag);
    }

    @DeleteMapping
    public Result<Boolean> clearTag() {
        Boolean res = tagService.clear();
        return Result.success(res);
    }

    @DeleteMapping("/{id}")
    public Result<Long> deleteTag(@PathVariable("id") String id) {
        Long count = tagService.deleteTag(id);
        return Result.success(count);
    }
}
