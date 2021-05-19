package com.profile.profileservice.controllers;

import com.profile.profileservice.entities.User;
import com.profile.profileservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        User user=new User("rm@gmail.com","RM","idol","image");
        userService.addUser(user);
        return "You can access this project!!";
    }

    @PutMapping("/update/{userId}/name/{userName}")
    public String updateName(@PathVariable String userId,@PathVariable String userName){
        userService.updateName(userId,userName);
        return "Name has been updated";

    }

    @PutMapping("/update/{userId}/bio/{bio}")
    public String updateBio(@PathVariable String userId,@PathVariable String bio){
        userService.updateBio(userId,bio);
        return "Bio has been updated";

    }


    @PutMapping("/upload/{userId}")
    public String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String userId) {
        String url = userService.uploadFile(file,userId);
        return "File uploaded successfully: File path :  " + url;
    }

    @GetMapping("/user/{userId}")
    public User getProfile(@PathVariable String userId){
        return userService.getUserById(userId);
    }
}
