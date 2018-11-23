package com.wei.eduyang.bean;

public class PageSize {

    private int start ;
    private int size ;

    public PageSize() {
    }

    public PageSize(int start, int size) {
        this.start = start;
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageSize{" +
                "start=" + start +
                ", size=" + size +
                '}';
    }
}
