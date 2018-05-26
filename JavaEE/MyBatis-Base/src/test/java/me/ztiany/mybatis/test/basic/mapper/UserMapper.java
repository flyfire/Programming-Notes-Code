package me.ztiany.mybatis.test.basic.mapper;

import me.ztiany.mybatis.pojo.User;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.26 21:16
 */
public interface UserMapper {


    /*
    遵循四个原则
        1. 接口方法名  == User.xml 中 id 名
        2. 返回值类型  与  Mapper.xml文件中返回值类型要一致
        3. 方法的入参类型 与Mapper.xml中入参的类型要一致
        4. 命名空间绑定此接口
     */
    User findUserById(Integer id);

}
