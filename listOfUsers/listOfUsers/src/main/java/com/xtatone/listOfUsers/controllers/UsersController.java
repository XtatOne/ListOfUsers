package com.xtatone.listOfUsers.controllers;

import com.xtatone.listOfUsers.entity.UserDetails;
import com.xtatone.listOfUsers.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xtatone.listOfUsers.service.UsersServeice.UsersService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/list")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {

        List<Users> allUsers = usersService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return ("all_Users");

    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {

        getUserwithServer(id, model);

        return "User";

    }

    @GetMapping("/new")
    public String showAddForm(Model model) {

        model.addAttribute("user", new Users());

        return "add_User";

    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") Users users, @RequestParam("userDetails") String userDetails) {

        users.setUserDetails(new UserDetails(userDetails));

        usersService.saveUser(users);

        return "redirect:/list/users";

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {

        getUserwithServer(id, model);

        return "edit_User";

    }

    @PutMapping("/users")
    public String editUser(@ModelAttribute("user") Users users, @RequestParam("userDetails") String userDetails) {

        usersService.saveUser(users);

        return "redirect:/list/users";

    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {

        usersService.deleteUser(id);

        return "redirect:/list/users";

    }

    private void getUserwithServer(int id, Model model) {

        Optional<Users> optionalUser = usersService.getUser(id);

        if (optionalUser.isPresent()) {

            Users user = optionalUser.get();

            model.addAttribute("user",          user);
            model.addAttribute("userDetails",   user.getUserDetails());

        }

    }

}


