package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.service.GjtService;
import com.riskers.githubx.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author riskers
 */
@RestController
@RequestMapping("/api/gjt")
public class GjtController {
    @Autowired
    private GjtService gjtService;

    @Autowired
    private StarService starService;

    @PostMapping(path = "/g/{gid}/t/{tid}")
    public Result<Long> addTagInGist(@PathVariable("gid") Long gid, @PathVariable("tid") String tid) {
        long count = starService.gjt(tid, gid);

        return Result.success(count);
    }

    @DeleteMapping(path = "/g/{gid}/t/{tid}")
    public Result<Long> delTagInGist(@PathVariable("gid") Long gid, @PathVariable("tid") String tid) {
        long count = gjtService.delTagInGist(tid, gid);

        return Result.success(count);
    }
}
