package com.riskers.githubx.entity;

import com.riskers.githubx.common.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author riskers
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Document(Const.STAR_COLLECTION_NAME)
public class Star extends Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 7870974602782169703L;

    @Id
    public String _id;

    public Long id;


    /**
     * repo fullName
     * ps. riskers/github-api
     */
    public String fullName;
}
