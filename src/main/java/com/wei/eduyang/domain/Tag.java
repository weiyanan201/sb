package com.wei.eduyang.domain;

import java.util.List;

public class Tag {
    private int id;
    private String tagName;
    private String tagDesc;
    private int tagParentId;

    private List<Tag> childTags;

    public Tag(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public int getTagParentId() {
        return tagParentId;
    }

    public void setTagParentId(int tagParentId) {
        this.tagParentId = tagParentId;
    }

    public List<Tag> getChildTags() {
        return childTags;
    }

    public void setChildTags(List<Tag> childTags) {
        this.childTags = childTags;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", tagDesc='" + tagDesc + '\'' +
                ", tagParentId=" + tagParentId +
                '}';
    }
}
