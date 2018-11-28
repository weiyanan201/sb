package com.wei.eduyang.domain;

import java.sql.Timestamp;

public class Plan {
    private int id;
    private String planName;
    private String planPath;
    private String planShowPath;
    private String planDesc;
    private Timestamp planCreateTime;
    private Timestamp planUpdateTime;

    private int tagCourse;
    private int tagScene;
    private int tagTheme;
    private int tagAge;
    private int tagSubject;




    public String getPlanShowPath() {
        return planShowPath;
    }

    public void setPlanShowPath(String planShowPath) {
        this.planShowPath = planShowPath;
    }

    public Plan(){

    }

    public Plan(int id, String planName, String planPath, String planShowPath, String planDesc, Timestamp planCreateTime, Timestamp planUpdateTime, int tagCourse, int tagScene, int tagTheme, int tagAge, int tagSubject) {
        this.id = id;
        this.planName = planName;
        this.planPath = planPath;
        this.planShowPath = planShowPath;
        this.planDesc = planDesc;
        this.planCreateTime = planCreateTime;
        this.planUpdateTime = planUpdateTime;
        this.tagCourse = tagCourse;
        this.tagScene = tagScene;
        this.tagTheme = tagTheme;
        this.tagAge = tagAge;
        this.tagSubject = tagSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanPath() {
        return planPath;
    }

    public void setPlanPath(String planPath) {
        this.planPath = planPath;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public Timestamp getPlanCreateTime() {
        return planCreateTime;
    }

    public void setPlanCreateTime(Timestamp planCreateTime) {
        this.planCreateTime = planCreateTime;
    }

    public Timestamp getPlanUpdateTime() {
        return planUpdateTime;
    }

    public void setPlanUpdateTime(Timestamp planUpdateTime) {
        this.planUpdateTime = planUpdateTime;
    }

    public int getTagCourse() {
        return tagCourse;
    }

    public void setTagCourse(int tagCourse) {
        this.tagCourse = tagCourse;
    }

    public int getTagScene() {
        return tagScene;
    }

    public void setTagScene(int tagScene) {
        this.tagScene = tagScene;
    }

    public int getTagTheme() {
        return tagTheme;
    }

    public void setTagTheme(int tagTheme) {
        this.tagTheme = tagTheme;
    }

    public int getTagAge() {
        return tagAge;
    }

    public void setTagAge(int tagAge) {
        this.tagAge = tagAge;
    }

    public int getTagSubject() {
        return tagSubject;
    }

    public void setTagSubject(int tagSubject) {
        this.tagSubject = tagSubject;
    }


    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", planPath='" + planPath + '\'' +
                ", planShowPath='" + planShowPath + '\'' +
                ", planDesc='" + planDesc + '\'' +
                ", planCreateTime=" + planCreateTime +
                ", planUpdateTime=" + planUpdateTime +
                ", tagCourse=" + tagCourse +
                ", tagScene=" + tagScene +
                ", tagTheme=" + tagTheme +
                ", tagAge=" + tagAge +
                ", tagSubject=" + tagSubject +
                '}';
    }
}
