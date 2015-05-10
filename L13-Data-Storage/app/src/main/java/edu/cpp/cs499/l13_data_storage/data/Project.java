package edu.cpp.cs499.l13_data_storage.data;

/**
 * Created by yusun on 5/10/15.
 */
public class Project {

    private String name;
    private String project;
    private int budget;

    public Project() {
    }

    public Project(String name, String project, int budget) {
        this.budget = budget;
        this.name = name;
        this.project = project;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return name + " " + project + " " + budget;
    }
}
