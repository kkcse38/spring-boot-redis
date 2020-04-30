package com.java.demoredis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.demoredis.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private RedisUserCrudRepository redisUserRepository;
	
	@GetMapping(value = "id/{id}")
	public User getUserById(@PathVariable String id) {
		return redisUserRepository.findById(id).get();
	}
	
	@PostMapping(value = "/add")
	public User addUser(@RequestBody User user) {
		return redisUserRepository.save(user);
	}
	
	@GetMapping
	public List<User> findAll() {
        List<User> list = new ArrayList<>(); 
		redisUserRepository.findAll().forEach(user->list.add(user));
		return list;
	}
	
	@PutMapping(value = "id/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		User redisUser = redisUserRepository.findById(id).get();
		redisUser.setName(user.getName());
		return redisUserRepository.save(redisUser);
	}
	
	@DeleteMapping(value = "id/{id}")
	public boolean updateUser(@PathVariable String id) {
		redisUserRepository.deleteById(id);
		return true;
	}
	
}
