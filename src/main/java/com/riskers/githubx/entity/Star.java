package com.riskers.githubx.entity;

import com.riskers.githubx.service.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author riskers
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Document(Const.STAR_COLLECTION_NAME)
public class Star extends Item {
    @Id
    public String _id;

    public Integer id;


    /**
     * repo fullName
     * ps. riskers/github-api
     */
    public String fullName;
}
