/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.nxl.hibernate;

import com.nxl.pojo.Category;
import com.nxl.pojo.Product;
import com.nxl.repository.impl.CategoryRepositoryImpl;
import com.nxl.repository.impl.ProductRepositoryImpl;
import com.nxl.repository.impl.StatsRepositoryImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class Hibernate {

    public static void main(String[] args) {
//        ProductRepositoryImpl p = new ProductRepositoryImpl();
//        Map<String, String> params = new HashMap<>();
//        params.put("fromPrice", "18000000");
//        params.put("toPrice", "25000000");
//        
//        p.getProducts(params).forEach(pi -> System.out.printf("%.0f\n", pi.getPrice()));
//        CategoryRepositoryImpl cri = new CategoryRepositoryImpl();
//        System.out.println(cri.getCategoryById(1).getName());

        StatsRepositoryImpl sri = new StatsRepositoryImpl();
        sri.statsRevenueByPeriod(2020, "QUARTER").forEach(s -> System.out.println(s[0] + " " + s[1]));
    }
}
