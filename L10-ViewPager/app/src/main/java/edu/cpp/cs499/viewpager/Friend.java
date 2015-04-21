package edu.cpp.cs499.viewpager;

/**
 * Created by yusun on 4/21/15.
 */
public class Friend {

    private String name;
    private int photoRes;

    public Friend(String name, int photoRes) {
        this.name = name;
        this.photoRes = photoRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoRes() {
        return photoRes;
    }

    public void setPhotoRes(int photoRes) {
        this.photoRes = photoRes;
    }
}
