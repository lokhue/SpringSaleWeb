/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nxl.hibernate;

import com.nxl.pojo.Category;
import com.nxl.pojo.Product;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author admin
 */
public class HibernateUtils {
    private static final SessionFactory factory;
    
    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/springsaledb");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "Admin@123");
        props.put(Environment.SHOW_SQL, "true");

        
        conf.setProperties(props);
        
        conf.addAnnotatedClass(Category.class);
        
        conf.addAnnotatedClass(Product.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        factory = conf.buildSessionFactory(registry);
    }

    /**
     * @return the factory
     */
    public static SessionFactory getFactory() {
        return factory;
    }
}
