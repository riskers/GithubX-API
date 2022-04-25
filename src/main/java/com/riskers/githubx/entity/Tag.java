package com.riskers.githubx.entity;

import org.springframework.data.annotation.Id;

/**
 * @author riskers
 */
public class Tag {
    @Id
    public Integer id;

    public String name;
}
