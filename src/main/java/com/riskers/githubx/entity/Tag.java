package com.riskers.githubx.entity;

import com.riskers.githubx.common.Const;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author riskers
 */
@Data
@Document(Const.TAGS_COLLECTION_NAME)
public class Tag implements Serializable {
    @Serial
    private static final long serialVersionUID = 3784250665623428854L;

    @Id
    public String id;

    public String name;

    public Tag(String _id, String name) {
        this.id = _id;
        this.name = name;
    }

    public Tag() {
    }
}
