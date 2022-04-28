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
@RestController
@RequestMapping(path = "/api/star")
public class StarsController {

    @Autowired
    private StarService starService;

    @PostMapping("/clear")
    public Result<Boolean> clear() {
        boolean res = starService.clearAll();
        return Result.success(res);
    }

    @PostMapping("/batchSave")
    public Result<Integer> batchSave(List<Star> stars) {
        Integer insertCount = starService.saveAll(stars);
        return Result.success(insertCount);
    }

    @GetMapping("/search")
    public Result<List<Star>> search(
            @RequestParam(required = false) Integer groupId, @RequestParam(required = false) Integer tagId, @RequestParam(required = false) String fullName
                                    ) {
        List<Star> res = starService.search(groupId, tagId, fullName);

        return Result.success(res);
    }
}
