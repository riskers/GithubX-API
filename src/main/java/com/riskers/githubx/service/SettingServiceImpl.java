package com.riskers.githubx.service;

import com.riskers.githubx.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author riskers
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Setting getSetting() {
        Query query = new Query();
        return mongoTemplate.findOne(query, Setting.class);
    }

    @Override
    public Setting setSetting(Setting setting) {
        setting.setCreateTime(System.currentTimeMillis());
        setting.setUpdateTime(System.currentTimeMillis());

        mongoTemplate.save(setting);
        return setting;
    }

    @Override
    public Boolean resetSetting() {
        mongoTemplate.dropCollection(Const.SETTINGS_COLLECTION_NAME);
        return true;
    }
}
