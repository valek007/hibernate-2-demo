package com.project.config;

import com.project.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // =============== TUS ENTIDADES ==================
            configuration.addAnnotatedClass(Actor.class);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(City.class);
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Film.class);
            configuration.addAnnotatedClass(FilmText.class);
            configuration.addAnnotatedClass(Inventory.class);
            configuration.addAnnotatedClass(Language.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Rental.class);
            configuration.addAnnotatedClass(Staff.class);
            configuration.addAnnotatedClass(Store.class);
            // =================================================

            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build()
            );

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

