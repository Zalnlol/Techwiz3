package fpt.aptech.hss.Model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private String mail;
    private String name;
    private String phone;
    private Date dob;
    private String gender;
    private String role;
    private String avatar;
    private String code;
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String mail, String name, String phone, Date dob, String gender, String role, String avatar, String code, String password) {
        this.mail = mail;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.role = role;
        this.avatar = avatar;
        this.code = code;
        this.password = password;
    }
}
