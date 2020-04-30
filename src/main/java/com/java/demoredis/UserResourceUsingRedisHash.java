package com.java.demoredis;

import java.util.Map;

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
@RequestMapping(value = "/user/hash")
public class UserResourceUsingRedisHash {

	@Autowired
	private IUserRepository userRepository;

	@GetMapping(value = "id/{id}")
	public User getUserById(@PathVariable String id) {
		return userRepository.findById(id);
	}

	@PostMapping(value = "/add")
	public User addUser(@RequestBody User user) {
		userRepository.save(user);
		return userRepository.findById(user.getId());
	}

	@GetMapping
	public Map<String,User> findAll() {
		return userRepository.findAll();
	}

	@PutMapping(value = "id/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		User redisUser = userRepository.findById(id);
		redisUser.setName(user.getName());
		userRepository.update(redisUser);
		return userRepository.findById(id);
	}

	@DeleteMapping(value = "id/{id}")
	public boolean deleteUser(@PathVariable String id) {
		userRepository.delete(id);
		return true;
	}

}
