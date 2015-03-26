package edu.cpp.cs499.l05_adapter_views;

/**
 * Created by yusun on 3/25/15.
 */
public class Contact {

    private String name;
    private String imageUrl;

    public Contact(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
