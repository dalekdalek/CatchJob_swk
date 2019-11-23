package com.example.catchjob_swk;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAccount implements Serializable {

    private String Name;
    private String Email;
    private String PhoneNum;
    private int Birth_Year;
    private int Birth_Month;
    private int Birth_Day;
    private boolean isIndividual;
    private String uid;

    public UserAccount(String name, String email, String phoneNum, int birth_Year, int birth_Month, int birth_Day, boolean isIndividual, String uid) {
        Name = name;
        Email = email;
        PhoneNum = phoneNum;
        Birth_Year = birth_Year;
        Birth_Month = birth_Month;
        Birth_Day = birth_Day;
        this.isIndividual = isIndividual;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }



    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    private ArrayList<String> followers;

    public UserAccount(){

    }
    public UserAccount(boolean isIndividual ,String Email, String Name, String PhoneNum, int Birth_Year, int Birth_Month, int Birth_Day) {

        this.isIndividual = isIndividual;
        this.Name = Name;
        this.Email = Email;
        this.PhoneNum = PhoneNum;
        this.Birth_Year = Birth_Year;
        this.Birth_Month = Birth_Month;
        this.Birth_Day = Birth_Day;
    }


    // Getter
    public boolean getisIndividual() {
        return this.isIndividual;
    }
    public String getName() {
        return this.Name;
    }
    public String getEmail() {
        return this.Email;
    }
    public String getPhoneNum() {
        return this.PhoneNum;
    }
    public int getBirthYear() {
        return Birth_Year;
    }
    public int getBirth_Month() {
        return Birth_Month;
    }
    public int getBirthDay() {
        return Birth_Day;
    }

    // Setter
    public void setName(String Name) {
        this.Name = Name;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }
    public void setBirthYear(int Birth_Year) {
        this.Birth_Year = Birth_Year;
    }
    public void setBirth_Month(int Birth_Month) {
        this.Birth_Month = Birth_Month;
    }
    public void setBirthDay(int Birth_Day) {
        this.Birth_Day = Birth_Day;
    }
    public void setisIndividual(boolean isIndividual) {
        this.isIndividual = isIndividual;
    }
}
