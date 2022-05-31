package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.service.SjtService;
import com.riskers.githubx.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author riskers
 *
 * star and tag join
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/sjt")
public class SjtController {

    @Autowired
    private SjtService sjtService;

    @Autowired
    private StarService starService;

    @PostMapping(path = "/s/{sid}/t/{tid}")
    public Result<Long> addTagInStar(@PathVariable("sid") Long sid, @PathVariable("tid") String tid) {
        long count = starService.sjt(tid, sid);

        return Result.success(count);
    }

    @DeleteMapping(path = "/s/{sid}/t/{tid}")
    public Result<Long> delTagInStar(@PathVariable("sid") Long sid, @PathVariable("tid") String tid) {
        long count = sjtService.delTagInStar(tid, sid);
        return Result.success(count);
    }
}
