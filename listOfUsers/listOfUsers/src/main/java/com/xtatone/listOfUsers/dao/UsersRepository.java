package com.xtatone.listOfUsers.dao;

import com.xtatone.listOfUsers.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT new Users(u.id, u.name, u.surname, u.department, u.userDetails, u.salary) " +
            "FROM Users u " +
            "WHERE u.salary > (SELECT AVG(u.salary) " +
            "FROM Users u)")
    List<Users> getUsersWithAvgSalary();

    @Query("SELECT AVG(u.salary) " +
            "FROM Users u")
    int getAvgSalary();

}
qwe