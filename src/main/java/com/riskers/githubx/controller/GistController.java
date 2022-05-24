package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.entity.Gist;
import com.riskers.githubx.service.GistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author riskers
 */
@RestController
@RequestMapping("/api/gist")
public class GistController {

    @Autowired
    private GistService gistService;

    @GetMapping
    public Result<List<Gist>> search(
            @RequestParam(required = false) Integer groupId, @RequestParam(required = false) String tagId, @RequestParam(required = false) String description
                                    ) {
        List<Gist> res = gistService.search(groupId, tagId, description);

        return Result.success(res);
    }

    @PostMapping
    public Result<Boolean> addGist(@RequestBody Gist gist) {
        boolean res = gistService.addGist(gist);
        return Result.success(res);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteGist(@PathVariable("id") Integer id) {
        boolean res = gistService.deleteGist(id);
        return Result.success(res);
    }
}
