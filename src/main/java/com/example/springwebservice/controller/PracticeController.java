package com.example.springwebservice.controller;


import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/practice/user")
public class PracticeController {
    @Autowired
    UserService userService;
    @GetMapping()
    String getAllUsers(Model model) {
        List<User> response = this.userService.getAllUsers();
        model.addAttribute("response", response);
        return "user";
    }
    @GetMapping("/search")
    public String getUser(@RequestParam int id, Model model) {
        User response = this.userService.getUser(id);
        model.addAttribute("response", response);
        return "user";
    }
    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new user");
        return "userForm";
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        this.userService.saveUser(user);
        return "redirect:/practice/user";
    }
    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = this.userService.get(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Edit user");
        return "createUser";
    }

    @PutMapping("/edit")
    public String updateUser(@ModelAttribute User user) {
        this.userService.updateUser(user);
        return "redirect:/practice/user";
    }
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") int id, Model model) {
        User user = this.userService.get(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Delete user");
        return "deleteUser";
    }

    @DeleteMapping("/delete")  // confirm to delete
    public String deleteUser(@ModelAttribute User user) {
        this.userService.deleteUserById(user.getId());
        return "redirect:/practice/user";
    }


}
