package com.shop.service;

import java.util.List;

import com.shop.pojo.Users;

public interface UsersService {

	public List<Users> getListUsers();

	public void saveUser(Users user);

	public Users findUsersByCode(String code);

	public void updateUser(Users user);

	// 登录功能
	public Users loginUser(String username, String password, int state);
}
