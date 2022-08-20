package com.revature.pokebowl.member.interfaces;
import java.util.List;
public interface Crudable<T> {

    // Create
    T create(T newObject);

    //Read
    List<T> findAll();
    T findById(String id);

    // Update
    boolean update(T updatedObject);

    //Delete
    boolean delete(String id);

}