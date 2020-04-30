package com.java.demoredis;

import java.util.Optional;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import com.java.demoredis.model.User;

//@EnableRedisRepositories
public interface RedisUserCrudRepository extends CrudRepository<User, String>{
	
	public Optional<User> findById(String id);
	
}
