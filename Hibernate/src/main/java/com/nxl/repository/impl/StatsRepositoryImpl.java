package com.nxl.repository.impl;


import com.nxl.hibernate.HibernateUtils;
import com.nxl.pojo.OrderDetail;
import com.nxl.pojo.Product;
import com.nxl.pojo.SaleOrder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class StatsRepositoryImpl {
    public List<Object[]> statsRevenueByProduct(){
        try (Session s = HibernateUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
            
            Root rP = q.from(Product.class);
            Root rO = q.from(OrderDetail.class);
            
            q.multiselect(rP.get("id"), rP.get("name"), b.sum(b.prod(rO.get("quantity"), rO.get("unitPrice"))));
            
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(b.equal(rO.get("productId"), rP.get("id")));
            
            q.where(predicates.toArray(Predicate[]::new));
            q.groupBy(rP.get("id"));
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
    
    public List<Object[]> statsRevenueByMonth(int year){
        try (Session s = HibernateUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
            
            Root rS = q.from(SaleOrder.class);
            Root rO = q.from(OrderDetail.class);
            
            q.multiselect(b.function("MONTH", Integer.class, rS.get("createdDate")), b.sum(b.prod(rO.get("quantity"), rO.get("unitPrice"))));
            
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(b.equal(rO.get("orderId"), rS.get("id")));
            predicates.add(b.equal(b.function("YEAR", Integer.class, rS.get("createdDate")), year));
            
            q.where(predicates.toArray(Predicate[]::new));
            q.groupBy(b.function("MONTH", Integer.class, rS.get("createdDate")));
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
    
    public List<Object[]> statsRevenueByPeriod(int year, String period){
        try (Session s = HibernateUtils.getFactory().openSession()){
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
            
            Root rS = q.from(SaleOrder.class);
            Root rO = q.from(OrderDetail.class);
            
            q.multiselect(b.function(period, Integer.class, rS.get("createdDate")), b.sum(b.prod(rO.get("quantity"), rO.get("unitPrice"))));
            
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(b.equal(rO.get("orderId"), rS.get("id")));
            predicates.add(b.equal(b.function("YEAR", Integer.class, rS.get("createdDate")), year));
            
            q.where(predicates.toArray(Predicate[]::new));
            q.groupBy(b.function(period, Integer.class, rS.get("createdDate")));
            
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
}
