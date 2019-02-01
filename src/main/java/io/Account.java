package io;


import java.sql.Date;
import java.util.List;

public class Account{
 private    Integer id;
       private   String email;
    private    String fname;
    private   String sname;
    private  String phone;
    private   String sex;
    private    String country;
    private   String city;
    private  Date joined;
    private   String status;
    private    Date  birth;
    private    List<String> interests;
    private   Premium premium;
    private   List<Likes> likesss;


public Account(Integer id,  String email, String fname, String sname, String phone, String sex, String country, String city, Date joined, String status, Date  birth,   List interests, Premium premium, List<Likes> likes){
this.id=id;
this.email=email;
this.fname=fname;
this.sname=sname;
this.phone=phone;
this.sex=sex;
this.birth=birth;
this.country=country;
this.city=city;
this.joined=joined;
this.status=status;
this.interests=interests;
this.premium=premium;
this.likesss=likes;

}


    public Account(Integer id,  String email, String fname, String sname, String phone, String sex, String country, String city, Date joined, String status, Date  birth){
        this.id=id;
        this.email=email;
        this.fname=fname;
        this.sname=sname;
        this.phone=phone;
        this.sex=sex;
        this.birth=birth;
        this.country=country;
        this.city=city;
        this.joined=joined;
        this.status=status;
      /*  this.interests=interests;
        this.premium=premium;
        this.likesss=likes;   */

    }


    public Account(){

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public Long getBirthLong() {
        return (birth.getTime())/1000;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public Date getJoined() {
        return joined;
    }

    public Long getJoinedLong() {
        return (joined.getTime())/1000;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public Premium getPremium() {
        return premium;
    }

    public void setPremium(Premium premium) {
        this.premium = premium;
    }

    public List<Likes> getLikesss() {
        return likesss;
    }

    public void setLikesss(List<Likes> likesss) {
        this.likesss = likesss;
    }
}
