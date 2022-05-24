package com.riskers.githubx.entity;

import com.riskers.githubx.common.Const;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author riskers
 */
@Data
@Document(Const.SETTINGS_COLLECTION_NAME)
public class Setting implements Serializable {
    @Serial
    private static final long serialVersionUID = -7154008039825016317L;

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
