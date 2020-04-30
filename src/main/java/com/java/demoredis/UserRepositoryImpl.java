package com.java.demoredis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.java.demoredis.model.User;

@Repository
public class UserRepositoryImpl implements IUserRepository{
	
	Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
//	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	private HashOperations<String, String, User> hashOperations;
//	
	public  UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = redisTemplate.opsForHash();
	}
	
	
	@Override
	public void save(User user) {
		hashOperations.put("USER", user.getId(), user);
		redisTemplate.expire("USER", 60, TimeUnit.SECONDS);
		logger.info("redisTemplate expire :{}",redisTemplate.getExpire("USER"));
	}

	@Override
	public void update(User user) {
		save(user);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete("USER", id);
	}

	@Override
	public Map<String, User> findAll() {
		return hashOperations.entries("USER");
	}

	@Override
	public User findById(String id) {
		return (User) hashOperations.get("USER", id);
	}

}
