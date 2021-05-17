package com.profile.profileservice.services;

import com.profile.profileservice.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UserService {
    public void updateName(long userId,String name);
    public void updateBio(long userId,String bio);
    public void addUser(User user);
    public String uploadFile(MultipartFile file,long userId);
    public User getUserById(long userId);

}
