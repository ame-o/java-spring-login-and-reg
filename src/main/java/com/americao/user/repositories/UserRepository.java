package com.americao.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.americao.user.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
	List<User> findAll();
}
