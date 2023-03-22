package com.attendance.entity;

// this entity is needed because we have a many-to-many relationship between students and classes
public class ClassStudent {
    private int relationshipId;
    private String className;
    private String studentUsername;
    private boolean isPresent;
    private String note;


}
