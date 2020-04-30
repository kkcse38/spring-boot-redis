package com.java.demoredis;

import java.util.Map;

import com.java.demoredis.model.User;

public interface IUserRepository {
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(String id);
	
	public Map<String, User> findAll();
	
	public User findById(String id);
}
