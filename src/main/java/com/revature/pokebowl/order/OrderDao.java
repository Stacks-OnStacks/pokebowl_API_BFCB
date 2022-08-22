package com.revature.pokebowl.order;

import com.revature.pokebowl.util.HibernateUtil;
import com.revature.pokebowl.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class OrderDao implements Crudable<Order> {

    @Override
    public Order create(Order newOrder) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.save(newOrder);
            transaction.commit();

            return newOrder;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            List<Order> orderList = session.createQuery("FROM Order").list();
            transaction.commit();

            return orderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<Order> findAllByMemberId(String memberId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Order where member_id = :memberId");
            query.setParameter("memberId", memberId);

            List<Order> orderList = query.list();
            transaction.commit();

            return orderList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Order findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Order order = session.get(Order.class, id);
            transaction.commit();

            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Order updatedOrder) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.merge(updatedOrder);
            transaction.commit();

            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Order order = session.load(Order.class, id);
            session.remove(order);
            transaction.commit();

            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
