package com.example.volod.zick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @Expose
    @SerializedName("sex")
    public char sex;

    @Expose
    @SerializedName("mail")
    public String mail;

    @Expose
    @SerializedName("password")
    public String password;

    @Expose
    @SerializedName("username")
    public String username;

    @Expose
    @SerializedName("created_at")
    public String created_at;

    @Expose
    @SerializedName("id")
    public long id;


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public String toString() {
        String data = username + "  " + password + "  " + id + " " + mail;
        return data;
    }

    public UserData(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
