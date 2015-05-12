package edu.cpp.cs499.demostorage;

/**
 * Created by yusun on 5/11/15.
 */
public class Project {

    private String name;
    private String member;
    private int budget;

    public Project() {

    }

    public Project(String name, String member, int budget) {
        this.budget = budget;
        this.member = member;
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
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

    @Override
    public String toString() {
        return
                "budget=" + budget +
                ", name='" + name +
                ", member='" + member;
    }
}
