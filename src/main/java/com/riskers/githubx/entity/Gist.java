package com.riskers.githubx.entity;

import com.riskers.githubx.common.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(Const.GISTS_COLLECTION_NAME)
public class Gist extends Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 2624780517108285486L;

    @Id
    public String _id;

    private Long id;

    public String description;
}
