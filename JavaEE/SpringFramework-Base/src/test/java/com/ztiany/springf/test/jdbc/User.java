package com.ztiany.springf.test.jdbc;

/*
create table springf_test_user (
    id int(20) NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    primary key (id)
);
 */
public class User {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }


}
