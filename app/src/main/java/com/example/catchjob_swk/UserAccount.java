package com.example.catchjob_swk;

public class UserAccount {

    private String Name;
    private String Email;
    private String PhoneNum;
    private int Birth_Year;
    private int Birth_Month;
    private int Birth_Day;

    public UserAccount(String Email, String Name, String PhoneNum, int Birth_Year, int Birth_Month, int Birth_Day) {

        this.Name = Name;
        this.Email = Email;
        this.PhoneNum = PhoneNum;
        this.Birth_Year = Birth_Year;
        this.Birth_Month = Birth_Month;
        this.Birth_Day = Birth_Day;

    }


    // Getter
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
}
