package com.profile.profileservice.controllers;

import com.profile.profileservice.entities.User;
import com.profile.profileservice.services.UserService;
import com.profile.profileservice.utils.Response;
import com.profile.profileservice.utils.UserIdFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserIdFetcher fetcher;

    @GetMapping("/welcome")
    public String welcome() {
        return "You can access this project!!";
    }

    @PutMapping("/name/{userName}")
    public ResponseEntity<Response> updateName(HttpServletRequest httpServletRequest,@PathVariable String userName){
        String userId=fetcher.getUserId(httpServletRequest);
        userService.updateName(userId,userName);
        Response<String> apiResponse = new Response<>("Success", HttpStatus.OK.value(), "The name has been updated");
        return new ResponseEntity<Response>(apiResponse, HttpStatus.OK);

    }

    @PutMapping("/bio/{bio}")
    public ResponseEntity<Response> updateBio(HttpServletRequest httpServletRequest, @PathVariable String bio){
        String userId=fetcher.getUserId(httpServletRequest);
        userService.updateBio(userId,bio);
        Response<String> apiResponse = new Response<>("Success", HttpStatus.OK.value(), "The Bio has been updated");
        return new ResponseEntity<Response>(apiResponse, HttpStatus.OK);

    }


    @PutMapping("/upload")
    public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest httpServletRequest) {
         String userId=fetcher.getUserId(httpServletRequest);
        String url = userService.uploadFile(file,userId);
        Response<String> apiResponse = new Response<>("Success", HttpStatus.OK.value(), "File uploaded successfully: File path :  " + url);
        return new ResponseEntity<Response>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Response> getProfile(@PathVariable String userId){
        User user= userService.getUserById(userId);
        Response<User> apiResponse = new Response<>("Success", HttpStatus.OK.value(), user);
        return new ResponseEntity<Response>(apiResponse, HttpStatus.OK);
    }
}
