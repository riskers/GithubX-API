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
@Document(Const.GROUPS_COLLECTION_NAME)
public class Group implements Serializable {

    @Serial
    private static final long serialVersionUID = 8881850022104082427L;

    @Id
    public String _id;

    public Long id;

    public String name;

    public Group(String _id, Long id, String name) {
        this._id = _id;
        this.id = id;
        this.name = name;
    }

    public Group() {
    }
}
