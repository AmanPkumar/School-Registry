package com.example.school;

public class ChildProfile {
    private String childName;
    private String childAge;
    private String childClass;
    private String contact;

    public ChildProfile(String name,String age, String division,String phoneNumber){
        this.childName = name;
        this.childAge = age;
        this.childClass = division;
        this.contact = phoneNumber;
    }

    public String getChildName(){
        return childName;
    }
    public String getChildAge(){
        return childAge;
    }
    public String getChildClass(){
        return childClass;
    }
    public String getContact(){
        return contact;
    }

}
