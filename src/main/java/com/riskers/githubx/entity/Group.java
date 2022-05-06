package com.riskers.githubx.entity;

import com.riskers.githubx.service.Const;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author riskers
 */
@Data
@Document(Const.GROUPS_COLLECTION_NAME)
public class Group {

    @Id
    public String _id;

    public Integer id;

    public String name;

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {
    }
}
