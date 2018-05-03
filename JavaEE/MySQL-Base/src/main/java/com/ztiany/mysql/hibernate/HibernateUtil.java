package com.ztiany.mysql.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;

    static {
        Configuration cfg = new Configuration();
        cfg.configure();
        factory = cfg.buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
