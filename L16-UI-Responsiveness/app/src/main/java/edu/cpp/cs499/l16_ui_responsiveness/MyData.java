package edu.cpp.cs499.l16_ui_responsiveness;

/**
 * Created by yusun on 5/12/15.
 */
public class MyData {

    private String name;
    private String member;

    public MyData() {
    }

    public MyData(String member, String name) {
        this.member = member;
        this.name = name;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
