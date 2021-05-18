package com.profile.profileservice.services;

import com.profile.profileservice.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UserService {
    public void updateName(String userId,String name);
    public void updateBio(String userId,String bio);
    public void addUser(User user);
    public String uploadFile(MultipartFile file,String userId);
    public User getUserById(String userId);

}
