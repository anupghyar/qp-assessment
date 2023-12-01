package com.test.qpassessment.service.impl;

import com.test.qpassessment.model.Role;
import com.test.qpassessment.model.User;
import com.test.qpassessment.repository.RoleRepository;
import com.test.qpassessment.repository.UserRepository;
import com.test.qpassessment.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public Collection<User> findAll() {
		
		Collection<User> users = userRepository.findAll();
		return users;
		
	}

//	@Override
//	public User findById(Long id) {
//
//		Optional<User> one = userRepository.findOne(id);
//		return user;
//
//	}

	@Override
	public User findByUsername(String username) {
		
		User user = userRepository.findByUsername(username);
		return user;
		
	}

	@Override
	public User findByEmail(String email) {

		User user = userRepository.findByEmail(email);
		return user;

	}

	@Override
	public User create(User user) throws BadRequestException {
		
		if (user.getEmail() == null || findByEmail(user.getEmail()) != null || user.getRole().getId() == null) {
			//Cannot create user without email, and email is unique to a user
			return null;
		}
		Optional<Role> optionalRole = roleRepository.findById(Long.valueOf(user.getRole().getId()));
		optionalRole.map(role -> {
			user.setRole(role);
			userRepository.save(user);
			return role;
		}).orElseThrow(() -> new BadRequestException("Role not present for given id"));
		return user;
		
	}

//	@Override
//	public User update(User user) {
//
//		User userPersisted = findById(user.getId());
//		if (userPersisted == null || user.getEmail() == null ) {
//			//Cannot update user that doesn't exist or user without email
//			return null;
//		}
//		user.setAccountEnabled(true);
//		userRepository.save(user);
//		return user;
//	}
//
//	@Override
//	public void delete(Long id) {
//		User user = findById(id);
//		if (id == null) {
//			return;
//		}
//
//		user.setAccountEnabled(false);
//		userRepository.save(user);
//	}
//
//	@Override
//	public Collection<User> findByaccountEnable(boolean accountEnable) {
//
//		Collection<User> users = userRepository.findByAccountEnabled(accountEnable);
//		return users;
//
//	}
	
}
