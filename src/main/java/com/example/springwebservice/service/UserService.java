package com.example.springwebservice.service;


import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;

    List<User> userList;


    public List<User> getAllUsers() {
        List<User> response = userRepository.findAll();
        return response;
    }

    public User getUser(int id) {
        User response = this.userRepository.findById(id);
        return response;
    }



    public List<User> getUserList(int age) {
        List<User> response = new ArrayList<>();
        if (0 < age) {
            response = userRepository.findByAgeGreaterThanEqual(age);
        } else {
            response = userRepository.findAll();
        }

        return response;
    }

    public List<User> getAllAgeDesc() {
        List<User> response = userRepository.findAllByUserByAgeDesc();
        return response;
    }

    public List<User> getByNameAndAge(int age, String name) {
        List<User> response = userRepository.findByNameAndAge(age, name);
        return response;
    }


   /* public String updateUserBySql(int id, UpdateUserRequest request) {
        int count = userRepository.updateUserBySql(request.getAge(), request.getName(), id);
        String response = "fail";
        if (0 < count) {
            response = "OK";
        }
        return response;
    }*/

   /* public String deleteByNameAndAge(String name, int age) {
        int count = userRepository.deleteByNameAndAge(name, age);
        String response = "fail";
        if (0 < count) {
            response = "OK";
        }
        return response;
    }*/

    public List<String> getAll() {
        List<User> response = userRepository.findAll();
        List<String> namelist = new ArrayList<>();
        List<String> namelist1 = new ArrayList<>();
        for (User user : response) {
            namelist.add(user.getName());
        }
        namelist1 = namelist.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return namelist1;
    }

    public String saveUser(User user) {
        userRepository.save(user);
        return "OK";
    }

    public User get(int id) {
        User result = userRepository.findById(id);

        return result;
    }

    public String updateUser(User user) {
        userRepository.updateUserBySql(user.getAge(),user.getName(), user.getId());

        return "updated";
    }
    public String deleteUserById(int id) {
        userRepository.deleteById(id);
        return "deleted";
    }


} // Class end
