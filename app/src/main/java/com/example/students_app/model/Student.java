package com.example.students_app.model;

public class Student {
    private String name;
    private String id;
    private String avatar_url;
    private Boolean cb;
    private String phone;
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student(String name, String id, Boolean cb, String  phone, String address) {
        this.name = name;
        this.id = id;
        this.cb = cb;
        this.phone = phone;
        this.address = address;
    }
    public Student(String name, String id, String avatar_url, Boolean cb) {
        this.name = name;
        this.id = id;
        this.avatar_url = avatar_url;
        this.cb = cb;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Boolean getCb() {
        return cb;
    }

    public void setCb(Boolean cb) {
        this.cb = cb;
    }

}
