package com.profile.profileservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "userProfile")
public class User {
    @Id
    @Field(name = "_id")
    private long userId;
    private String name;
    private String bio;
    private String image;

    public User() {
    }

    public User(long userId, String name, String bio) {
        this.userId = userId;
        this.name = name;
        this.bio = bio;
    }
    public User(long userId, String name, String bio, String image) {
        this.userId = userId;
        this.name = name;
        this.bio = bio;
        this.image = image;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
