package com.ztiany.mysql.hibernate;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {

    private int id;
    private String name;
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
    }

}
