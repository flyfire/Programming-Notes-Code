package com.ztiany.mysql.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * ORM数据库框架Hibernate简单示例
 */
public class CRUDDemo {

    public static void main(String... args) {
        CRUDDemo crudDemo = new CRUDDemo();
        crudDemo.add();
        crudDemo.find();
        crudDemo.update();
        crudDemo.find();
        crudDemo.del();
    }

    public void add() {
        Customer c = new Customer();
        c.setName("杀生丸");
        c.setBirthday(new Date());

        Session s = HibernateUtil.getSession();

        Transaction tx = s.beginTransaction();//开启事务
        s.save(c);
        tx.commit();

        s.close();
    }


    public void find() {
        Session s = HibernateUtil.getSession();
        Customer c = (Customer) s.get(Customer.class, 2);
        System.out.println(c);
        s.close();
    }

    public void update() {
        Session s = HibernateUtil.getSession();

        Transaction tx = s.beginTransaction();//开启事务

        Customer c = (Customer) s.get(Customer.class, 1);
        c.setName("杀生丸");
        s.update(c);

        tx.commit();

        s.close();
    }


    public void del() {
        Session s = HibernateUtil.getSession();

        Transaction tx = s.beginTransaction();//开启事务

        Customer c = (Customer) s.get(Customer.class, 1);
        s.delete(c);

        tx.commit();

        s.close();
    }
}
