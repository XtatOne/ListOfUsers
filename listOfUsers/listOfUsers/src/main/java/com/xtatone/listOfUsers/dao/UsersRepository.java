package com.xtatone.listOfUsers.dao;

import com.xtatone.listOfUsers.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
