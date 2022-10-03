package com.example.myjourney.models;

public class UserRegular {
    // fields
    String UserName ,mEmail ,id, gender ;
    String Picture;
    int age ,weight,height;
    int counter;


    // constructors
    public UserRegular (String id ,String UserName,String mEmail,String gender,int age,int weight,int height){
        this.id =id;
        this.UserName = UserName;
        this.mEmail = mEmail;
        this.gender = gender;
        this.age=age;
        this.weight=weight;
        this.height=height;
    }


    public UserRegular (String id ,String UserName,String mEmail,String gender){
        this.id =id;
        this.UserName = UserName;
        this.mEmail = mEmail;
        this.gender = gender;
    }


    //Getters&Setters

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
