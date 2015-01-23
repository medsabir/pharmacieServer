package util;

import org.hibernate.*;
import org.hibernate.cfg.*;

import dao.Pharmacie;

public class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        try {
            // Cr�ation de la SessionFactory � partir de hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure("Hibernate.cfg.xml").addAnnotatedClass(Pharmacie.class).buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

   
   // public static final ThreadLocal session = new ThreadLocal();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
