package com.riskers.githubx.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author riskers
 */
@Data
public class Group {

    @Id
    public String _id;

    public Integer id;

    public String name;
}
