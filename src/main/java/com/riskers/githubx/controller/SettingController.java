package com.riskers.githubx.controller;

import com.riskers.githubx.common.Result;
import com.riskers.githubx.entity.Setting;
import com.riskers.githubx.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author riskers
 */
@RestController
@RequestMapping(path = "/api/setting")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @GetMapping
    public Result<Setting> getSetting() {
        return Result.success(settingService.getSetting());
    }

    @PostMapping
    public Result<Setting> updateSetting(@RequestBody Setting setting) {
        settingService.setSetting(setting);
        return Result.success(setting);
    }

    @DeleteMapping
    public Result<Boolean> deleteSetting() {
        Boolean res = settingService.resetSetting();
        return Result.success(res);
    }
}
