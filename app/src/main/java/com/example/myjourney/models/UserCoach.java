package com.example.myjourney.models;

public class UserCoach {
    String CoachUserName ,mEmail ,id ;
    String Picture;
    String age ;
    String address;
    String experience;
    String education;
    String gender;
    int counter;

    public UserCoach (String id ,String CoachUserName,String mEmail,String experience,String education,String age ,String gender,String address){
        this.id =id;
        this.CoachUserName = CoachUserName;
        this.mEmail = mEmail;
        this.age =age;
        this.education =education;
        this.experience =experience;
        this.gender =gender;
        this.address = address;

    }


    public UserCoach (String id ,String CoachUserName,String experience,String education,String age ,String address){
        this.id =id;
        this.CoachUserName = CoachUserName;
        this.age =age;
        this.education =education;
        this.experience =experience;
        this.address =address;
    }
//    public UserCoach (String picture ,String CoachUserName,String experience,String education,String age ,String address){
//        this.Picture =picture;
//        this.CoachUserName = CoachUserName;
//        this.age =age;
//        this.education =education;
//        this.experience =experience;
//
//    }



    public String getCoachUserName() {
        return CoachUserName;
    }

    public void setCoachUserName(String coachUserName) {
        CoachUserName = coachUserName;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
