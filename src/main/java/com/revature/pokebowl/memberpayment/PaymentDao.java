package com.revature.pokebowl.memberpayment;

import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.order.Order;
import com.revature.pokebowl.util.HibernateUtil;
import com.revature.pokebowl.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class PaymentDao implements Crudable<Payment> {

    @Override
    public Payment create(Payment newPayment) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newPayment);
            transaction.commit();
            return newPayment;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    @Override
    public List<Payment> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Payment> paymentList = session.createQuery("FROM Payment").list();
            transaction.commit();
            return paymentList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public List<Payment> findAllByPaymentId(String paymentId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Payment where payment_id = :paymentId");
            query.setParameter("paymentId", paymentId);

            List<Payment> paymentList = query.list();
            transaction.commit();

            return paymentList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Payment findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Payment payment = session.get(Payment.class, id);
            transaction.commit();

            return payment;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public Payment findByName(String paymentName) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Payment where payment_name = :paymentName");
            query.setParameter("paymentName", paymentName);

            Payment payment = (Payment) query.uniqueResult();
            transaction.commit();

            return payment;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Payment updatedPayment) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedPayment);
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
            Payment payment = session.load(Payment.class, id);
            System.out.println("HELLO---1");
            session.remove(payment);
            System.out.println("HELLO---2");
            transaction.commit();
            System.out.println("HELLO---3");
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public boolean checkPaymentName(String paymentName) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Payment where payment_name = :paymentName");
            query.setParameter("paymentName", paymentName);

            Payment payment = (Payment) query.uniqueResult();
            transaction.commit();

            if(payment == null) return true;
            return false;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public List<Payment> findAllByMemberId(String memberId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Payment where member_id = :memberId");
            query.setParameter("memberId", memberId);
            List<Payment> paymentList = query.list();
            transaction.commit();
            return paymentList;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
    public Payment findByIdAndMember(String paymentId, String memberId) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Payment where payment_id = :paymentId and member_id = :memberId");
            query.setParameter("paymentId", paymentId);
            query.setParameter("memberId", memberId);
            Payment payment = (Payment) query.uniqueResult();
            transaction.commit();
            return payment;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
