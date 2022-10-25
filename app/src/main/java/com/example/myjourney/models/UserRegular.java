package com.example.myjourney.models;

public class UserRegular {
    // fields
    String UserName ,mEmail ,id, gender ;
    String Picture;
    String age ,weight,height;
    String shoes;
    int counter;


    // constructors
//    public UserRegular (String id ,String UserName,String mEmail,String gender,String age,String weight,String height){
//        this.id =id;
//        this.UserName = UserName;
//        this.mEmail = mEmail;
//        this.gender = gender;
//        this.age=age;
//        this.weight=weight;
//        this.height=height;
//    }


    public UserRegular (String id ,String UserName,String mEmail,String gender,String weight,String height,String age ,String shoes){
        this.id =id;
        this.UserName = UserName;
        this.mEmail = mEmail;
        this.gender = gender;
        this.height=height;
        this.age =age;
        this.weight =weight;
        this.shoes =shoes;
    }

    public UserRegular (String id ,String UserName,String gender,String weight,String height,String age){
        this.id =id;
        this.UserName = UserName;
        this.gender = gender;
        this.height=height;
        this.age =age;
        this.weight =weight;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
