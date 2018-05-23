package com.ztiany.springf;

import com.ztiany.springf.domain.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.23 23:28
 */
//帮我们创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JUnitTest {

    //将名为user的对象注入到u变量中
    @Resource(name = "user1")
    private User mUser;

    @Test
    public void fun1() {
        System.out.println(mUser);
        assertNotNull(mUser);
    }

}
