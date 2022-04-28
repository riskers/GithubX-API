package com.riskers.githubx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author riskers
 */
@Data
@Document("groups")
public class Group {

    @Id
    public String _id;

    public Integer id;

    public String name;
}
