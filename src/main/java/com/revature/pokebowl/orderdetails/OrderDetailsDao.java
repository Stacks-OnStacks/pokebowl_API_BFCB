package com.revature.pokebowl.orderdetails;

import com.revature.pokebowl.util.HibernateUtil;
import com.revature.pokebowl.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class OrderDetailsDao implements Crudable<OrderDetails> {

    @Override
    public OrderDetails create(OrderDetails newOrderDetails) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.save(newOrderDetails);
            transaction.commit();

            return newOrderDetails;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<OrderDetails> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<OrderDetails> orderDetailsList = session.createQuery("FROM OrderDetails").list();
            transaction.commit();

            return orderDetailsList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<OrderDetails> findAllByOrderId(String orderId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from OrderDetails where order_id = :orderId");
            query.setParameter("orderId", orderId);

            List<OrderDetails> orderDetailsList = query.list();
            transaction.commit();

            return orderDetailsList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public OrderDetails findAllByIdAndOrder(String orderDetailsId, String orderId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from OrderDetails where order_id = :orderId and order_details_id = :orderDetailsId");
            query.setParameter("orderId", orderId);
            query.setParameter("orderDetailsId", orderDetailsId);

            OrderDetails orderDetails = (OrderDetails) query.uniqueResult();
            transaction.commit();

            return orderDetails;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public OrderDetails findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            OrderDetails orderDetails = session.get(OrderDetails.class, id);
            transaction.commit();

            return orderDetails;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public OrderDetails findByDishId(String orderId, String dishId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from OrderDetails where order_id = :orderId and dish_id = :dishId");
            query.setParameter("orderId", orderId);
            query.setParameter("dishId", dishId);

            OrderDetails orderDetails = (OrderDetails) query.uniqueResult();
            transaction.commit();

            return orderDetails;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(OrderDetails updatedOrderDetails) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.merge(updatedOrderDetails);
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

            OrderDetails orderDetails = session.load(OrderDetails.class, id);
            session.remove(orderDetails);
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
