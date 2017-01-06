package com.justzaap.auth.security.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.justzaap.auth.security.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findByUserName(String userName);

	public List<User> findByTbluserRoles_tblrole_name(String roleName);

	public User findByUserIdAndIsDeleted(String userId, String isDeleted);

}
