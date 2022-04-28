package com.riskers.githubx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author riskers
 */
@Data
@Document("tags")
public class Tag {
    @Id
    public String _id;

    public Integer id;

    public String name;
}
