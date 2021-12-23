package com.xtatone.listOfUsers.service.UsersServeice;

import com.xtatone.listOfUsers.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<Users> getAllUsers();

    Optional<Users> getUser(int id);

    void saveUser(Users users);

    void deleteUser(int id);

    List<Users> getUsersWithAvgSalary();

    int getAvgSalary();

}
