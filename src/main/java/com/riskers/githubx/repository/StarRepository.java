package com.riskers.githubx.repository;

import com.riskers.githubx.entity.Star;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author riskers
 */
@EnableMongoRepositories
public interface StarRepository extends MongoRepository<Star, String> {

}
