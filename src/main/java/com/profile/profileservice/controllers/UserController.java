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
        User user=new User(2L,"anu","adventure","image");
        userService.addUser(user);
        return "You can access this project!!";
    }

    @PutMapping("/update/{userId}/name/{userName}")
    public ResponseEntity<String>updateName(@PathVariable long userId,@PathVariable String userName){
        userService.updateName(userId,userName);
        return new ResponseEntity<>("Successful update", HttpStatus.CREATED);

    }

    @PutMapping("/update/{userId}/bio/{bio}")
    public ResponseEntity<String>updateBio(@PathVariable long userId,@PathVariable String bio){
        userService.updateBio(userId,bio);
        return new ResponseEntity<>("Successful update", HttpStatus.CREATED);

    }


    @PutMapping("/upload/{userId}")
    public String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable long userId) {
        String url = userService.uploadFile(file,userId);
        return "File uploaded successfully: File path :  " + url;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User>getProfile(@PathVariable long userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }
}
