package me.ztiany.mybatis.test.basic;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

import me.ztiany.mybatis.pojo.User;
import me.ztiany.mybatis.test.basic.mapper.UserMapper;

/**
 * MyBatis  + Mapper 动态代理开发(不需要开发者自己编写Mapper实现，MyBatis根据接口中定义的方法签名自动定位mapper.xml 中配置的 sql)
 */
public class MybatisMapperTest {


    @Test
    public void testMapper() throws Exception {
        //加载核心配置文件
        InputStream in = Resources.getResourceAsStream("sqlMapConfig_Mapper.xml");

        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //SqlSession自动生成一个实现类  （给接口）
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(10);

        System.out.println(user);
        System.out.println(userMapper);

        sqlSession.close();
    }
}
