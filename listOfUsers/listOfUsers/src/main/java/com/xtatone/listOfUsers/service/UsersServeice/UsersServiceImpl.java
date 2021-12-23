package com.xtatone.listOfUsers.service.UsersServeice;

import com.xtatone.listOfUsers.dao.UsersRepository;
import com.xtatone.listOfUsers.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    public UsersRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void saveUser(Users users) {
        userRepository.save(users);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Users> getUsersWithAvgSalary() {
        return userRepository.getUsersWithAvgSalary();
    }

    @Override
    public int getAvgSalary() {
        return userRepository.getAvgSalary();
    }
}
