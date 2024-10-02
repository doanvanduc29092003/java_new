package com.microfvn.servicer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microfvn.models.User;
import com.microfvn.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServicer{
	@Autowired
	private UserRepository userRepository;
	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

}
