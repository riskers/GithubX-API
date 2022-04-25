package com.riskers.githubx.controller;

import com.riskers.githubx.entity.Star;
import com.riskers.githubx.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author riskers
 */
@RestController
public class StarsController {

    @Autowired
    private StarService starService;

    @GetMapping("/api/test")
    public Star test() {
        Star star = new Star();
        star.groupId = 1;

        return starService.save(star);
    }
}
