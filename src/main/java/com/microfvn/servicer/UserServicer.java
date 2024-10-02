package com.microfvn.servicer;

import com.microfvn.models.User;

public interface UserServicer {
	User findByUserName(String username);
}
