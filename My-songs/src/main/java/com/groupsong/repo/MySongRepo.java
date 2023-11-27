package com.groupsong.repo;

import com.groupsong.domain.MySong;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySongRepo extends MongoRepository<MySong,String> {
}
