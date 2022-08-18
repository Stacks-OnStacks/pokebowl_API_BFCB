package com.revature.pokebowl.dish;

import com.revature.pokebowl.member.Member;
import com.revature.pokebowl.util.HibernateUtil;
import com.revature.pokebowl.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class DishDao implements Crudable<Dish> {

    @Override
    public Dish create(Dish newDish) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.save(newDish);
            transaction.commit();

            return newDish;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Dish> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            List<Dish> dishes = session.createQuery("FROM dishes").list();
            transaction.commit();

            return dishes;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Dish findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            Dish dish = session.get(Dish.class, id);
            transaction.commit();

            return dish;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Dish updatedDish) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

            session.merge(updatedDish);
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

            Dish dish = session.load(Dish.class, id);
            session.remove(dish);

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
