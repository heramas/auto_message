package com.auto.message.dao;

import java.util.List;

import com.auto.message.dto.UserDTO;

public interface UserDAO {
	
	List<UserDTO> selectUsers(UserDTO param) throws Exception;
	
}
