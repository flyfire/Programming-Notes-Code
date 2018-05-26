package me.ztiany.mybatis.test.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import me.ztiany.mybatis.pojo.User;

/**
 * 测试MyBatis1
 *
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.26 22:16
 */
public class MyBatisBasicTest {

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //加载核心配置文件
        InputStream in = Resources.getResourceAsStream("sqlMapConfig_Basic.xml");
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void testMybatis() {
        //执行Sql语句：命名空间.sqlID
        User user = sqlSession.selectOne("test.findUserById", 10);
        System.out.println(user);
    }

    //根据用户名称模糊查询用户列表
    @Test
    public void testFindUserByUsername() {
        //执行Sql语句
        List<User> users = sqlSession.selectList("test.findUserByUsername", "五");
        for (User user : users) {
            System.out.println(user);
        }
    }

    //添加用户
    @Test
    public void testInsertUser() {
        //执行Sql语句
        User user = new User();
        user.setUsername("何炅");
        user.setBirthday(new Date());
        user.setAddress("湖南");
        user.setSex("男");
        int i = sqlSession.insert("test.insertUser", user);
        sqlSession.commit();
        System.out.println(i);
        System.out.println(user.getId());
    }

    //更新用户
    @Test
    public void testUpdateUserById() {
        //执行Sql语句
        User user = new User();
        user.setId(27);
        user.setUsername("何炅哥");
        user.setAddress("湖南岳阳");
        int i = sqlSession.update("test.updateUserById", user);
        sqlSession.commit();
        System.out.println(i);
    }

    //删除
    @Test
    public void testDelete() {
        sqlSession.delete("test.deleteUserById", 27);
        sqlSession.commit();
    }

}
