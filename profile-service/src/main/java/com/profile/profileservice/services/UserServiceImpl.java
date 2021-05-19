package com.profile.profileservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.profile.profileservice.entities.User;
import com.profile.profileservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinaryConfig;

    @Override
    public void updateName(String userId, String name) {
        User user=userRepository.findByUserId(userId);
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public void updateBio(String userId, String bio) {
        User user=userRepository.findByUserId(userId);
        user.setBio(bio);
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public String uploadFile(MultipartFile file,String userId) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            updateProfilePicture(uploadResult.get("url").toString(),userId);
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void updateProfilePicture(String picture,String userId){
        User user=getUserById(userId);
        user.setImage(picture);
        userRepository.save(user);
    }

    @Override
    public User getUserById(String userId){
        User tempUser=userRepository.findByUserId(userId);
        return tempUser;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
