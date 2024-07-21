package com.dcp.ap_service.v1.services;

import com.dcp.ap_service.v1.entities.User;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();
	User getUserByEmail(String email);
	List<User> getAllPremiumUsers();
}
