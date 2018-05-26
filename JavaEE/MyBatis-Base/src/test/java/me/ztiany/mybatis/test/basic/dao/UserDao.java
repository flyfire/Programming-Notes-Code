package me.ztiany.mybatis.test.basic.dao;


import me.ztiany.mybatis.pojo.User;

public interface UserDao {

    //通过用户ID查询一个用户
    User selectUserById(Integer id);


}
