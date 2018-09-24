package com.wei.eduyang.domain;

import java.sql.Timestamp;
import java.util.List;

public class Plan {
    private int id;
    private String planName;
    private String planPath;
    private String planDesc;
    private Timestamp planCreateTime;
    private Timestamp planUpdateTime;

    /**
     * 非实例化
     */
    private List<Plan> childPlans;

    public Plan(){

    }

    public Plan(String planName, String planPath, String planDesc, Timestamp planCreateTime, Timestamp planUpdateTime) {
        this.planName = planName;
        this.planPath = planPath;
        this.planDesc = planDesc;
        this.planCreateTime = planCreateTime;
        this.planUpdateTime = planUpdateTime;
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

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", planName='" + planName + '\'' +
                ", planPath='" + planPath + '\'' +
                ", planDesc='" + planDesc + '\'' +
                ", planCreateTime=" + planCreateTime +
                ", planUpdateTime=" + planUpdateTime +
                '}';
    }
}
