package com.xtatone.listOfUsers.service.UsersServeice;

import com.xtatone.listOfUsers.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    public List<Users> getAllUsers();

    public Optional<Users> getUser(int id);

    public void saveUser(Users users);

    public void deleteUser(int id);

}
