package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.entity.Star;
import com.riskers.githubx.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author riskers
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/star")
public class StarsController {

    @Autowired
    private StarService starService;

    @DeleteMapping
    public Result<Boolean> clearAllStar() {
        boolean res = starService.clearAll();
        return Result.success(res);
    }

    @PostMapping
    public Result<Boolean> addStar(@RequestBody Star star) {
        boolean res = starService.addStar(star);
        return Result.success(res);
    }

    @PostMapping("/list")
    public Result<Integer> batchSave(@RequestBody List<Star> stars) {
        Integer insertCount = starService.saveAll(stars);
        return Result.success(insertCount);
    }

    @GetMapping
    public Result<List<Star>> search(
            @RequestParam(required = false) Integer groupId, @RequestParam(required = false) String tagId, @RequestParam(required = false) String fullName
                                    ) {
        List<Star> res = starService.search(groupId, tagId, fullName);

        return Result.success(res);
    }

    @GetMapping("/{id}")
    public Result<Star> getStarInfo(@PathVariable("id") Long id) {
        Star res = starService.getStarInfo(id);
        return Result.success(res);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteStar(@PathVariable("id") Integer id) {
        boolean res = starService.deleteStar(id);
        return Result.success(res);
    }

}
