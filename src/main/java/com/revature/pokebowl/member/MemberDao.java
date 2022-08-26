package com.revature.pokebowl.member;

import com.revature.pokebowl.util.HibernateUtil;
import com.revature.pokebowl.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
import java.util.List;

public class MemberDao implements Crudable<Member> {


    @Override
    public Member create(Member newMember) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newMember);
            transaction.commit();
            return newMember;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Member> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            List<Member> members = session.createQuery("FROM Member").list();
            transaction.commit();

            return members;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Member findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Member member = session.get(Member.class, id);
            transaction.commit();

            return member;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Member updatedMember) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.merge(updatedMember);
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

            Member member = session.load(Member.class, id);
            session.remove(member);
            transaction.commit();

            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public Member loginCredentialCheck(String username, String password) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Member where username= :username and user_password= :password");
            query.setParameter("username", username);
            query.setParameter("password", password);

            Member member = (Member) query.uniqueResult();
            transaction.commit();

            return member;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public boolean checkUsername(String username) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from Member where username = :username");
            query.setParameter("username", username);

            Member member = (Member) query.uniqueResult();
            transaction.commit();

            if(member == null) return true;
            return false;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}