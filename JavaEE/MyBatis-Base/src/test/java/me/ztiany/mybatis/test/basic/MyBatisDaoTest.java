package me.ztiany.mybatis.test.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import me.ztiany.mybatis.pojo.User;
import me.ztiany.mybatis.test.basic.dao.UserDao;
import me.ztiany.mybatis.test.basic.dao.UserDaoImpl;

/**
 * MyBatis + 传统 DAO 模式：DAO编写繁复
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.26 22:58
 */
public class MyBatisDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception {
        //加载核心配置文件
        InputStream in = Resources.getResourceAsStream("sqlMapConfig_Basic.xml");
        //创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    @Test
    public void testDao() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.selectUserById(10);
        System.out.println(user);
    }

}
