package com.pizza.pizzabytes.DatabaseClasses;

public class ReadWriteUserDetails
{
    String fullname,PhoneNumber,password,repassword,email,country,city,location,gendername,dob;

    public ReadWriteUserDetails() {
    }

    public ReadWriteUserDetails(String fullname, String newotptext, String password, String email, String country, String city, String location, String gendername, String dob) {
        this.fullname = fullname;
        this.PhoneNumber = newotptext;
        this.password = password;
//        this.repassword = repassword;
        this.email = email;
        this.country = country;
        this.city = city;
        this.location = location;
        this.gendername = gendername;
        this.dob = dob;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNewotptext() {
        return PhoneNumber;
    }

    public void setNewotptext(String newotptext) {
        this.PhoneNumber = newotptext;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRepassword() {
//        return repassword;
//    }
//
//    public void setRepassword(String repassword) {
//        this.repassword = repassword;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGendername() {
        return gendername;
    }

    public void setGendername(String gendername) {
        this.gendername = gendername;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
