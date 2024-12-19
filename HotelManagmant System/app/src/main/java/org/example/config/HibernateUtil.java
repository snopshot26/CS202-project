package org.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        SessionFactory tempSessionFactory = null;
        try {
            tempSessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (org.hibernate.MappingException e) {
            System.err.println("MappingException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
        } finally {
            sessionFactory = tempSessionFactory;
        }
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory is not initialized.");
        }
        return sessionFactory;
    }
    
    public static void shutdown() {
        getSessionFactory().close();
    }
}
