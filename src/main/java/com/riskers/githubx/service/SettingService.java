package com.riskers.githubx.service;

import com.riskers.githubx.entity.Setting;

/**
 * @author riskers
 */
public interface SettingService {
    public Setting getSetting();

    public Setting setSetting(String token);

    public Boolean resetSetting();
}
