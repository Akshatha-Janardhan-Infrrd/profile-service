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
    public void updateName(long userId, String name) {
        Optional<User> optionalUser=userRepository.findById(userId);
        User user= optionalUser.get();
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public void updateBio(long userId, String bio) {
        Optional<User> optionalUser=userRepository.findById(userId);
        User user= optionalUser.get();
        user.setBio(bio);
        userRepository.save(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public String uploadFile(MultipartFile file,long userId) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            updateProfilePicture(uploadResult.get("url").toString(),userId);
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void updateProfilePicture(String picture,long userId){
        User user=getUserById(userId);
        user.setImage(picture);
        userRepository.save(user);
    }

    @Override
    public User getUserById(long userId){
        Optional<User> tempUser=userRepository.findById(userId);
        return tempUser.get();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
