package com.example.yuzelli.SmartTour.entity;


public class LifeInfo {
    private int pic;
    private String name;
    private String grade;
    private String content;

    public LifeInfo(int pic, String grade, String content, String name ) {
        this.pic = pic;
        this.name = name;
        this.grade = grade;
        this.content = content;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
