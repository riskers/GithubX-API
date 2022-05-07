package com.riskers.githubx.entity;

import com.riskers.githubx.service.Const;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author riskers
 */
@Data
@Document(Const.SETTINGS_COLLECTION_NAME)
public class Setting {
    /**
     * createTime timestamp
     */
    public Long createTime;

    /**
     * updateTime timestamp
     */
    public Long updateTime;

    /**
     * github token
     */
    public String token;
}
