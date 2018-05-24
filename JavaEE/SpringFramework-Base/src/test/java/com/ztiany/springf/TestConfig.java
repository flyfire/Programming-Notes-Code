package com.ztiany.springf;

import com.ztiany.springf.domain.CollectionBean;
import com.ztiany.springf.domain.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 配置文件测试
 */
public class TestConfig {

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        //1 创建容器对象
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testApplicationContext() {
        //2 向容器"要"user对象
        User user = (User) applicationContext.getBean("user1");
        assertNotNull(user);
    }

    @Test
    public void testBeanFactory() {
        //1 创建容器对象
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        //2 向容器"要"user对象
        User user = (User) factory.getBean("user1");
        assertNotNull(user);
    }

    @Test
    public void testScope() {
        User user1First = (User) applicationContext.getBean("user1");
        User user1Second = (User) applicationContext.getBean("user1");
        assertNotEquals(user1First, user1Second);
    }

    @Test
    public void testLifecycle() {
        User user2 = (User) applicationContext.getBean("user2");
        assertNotNull(user2);
    }

    @Test
    public void testInjection() {
        CollectionBean collection_bean = (CollectionBean) applicationContext.getBean("collection_bean");
        System.out.println(collection_bean);
        assertNotNull(collection_bean);
    }

    @After
    public void destroy() {
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}