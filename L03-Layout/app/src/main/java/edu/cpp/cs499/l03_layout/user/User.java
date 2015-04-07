package edu.cpp.cs499.l03_layout.user;

/**
 * Created by yusun on 4/7/15.
 */
public class User {

    private String name;
    private int age;
    private int photoRes;
    private int photos;
    private int messages;
    private int friends;

    public User(String name, int age, int photoRes, int photos, int messages, int friends) {
        this.name = name;
        this.age = age;
        this.photoRes = photoRes;
        this.photos = photos;
        this.messages = messages;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }
}
