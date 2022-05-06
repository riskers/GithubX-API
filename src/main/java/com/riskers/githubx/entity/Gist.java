package com.riskers.githubx.entity;

import com.riskers.githubx.service.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(Const.GISTS_COLLECTION_NAME)
public class Gist extends Item {
    @Id
    public String _id;

    public String description;
}
