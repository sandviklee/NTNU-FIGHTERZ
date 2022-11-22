package fightinggame.dao;

import java.util.ArrayList;

/**
 * The RODAO (Read Only Data Access Object) interface is a DAO only meant for
 * reading elements of type {@code T} with primary key {@code K}. All RODAO
 * implementations will have a {@link #getAll()} method to find all Objects of
 * type {@code T}, or only {@link #find(K id)} the first entity with given id.
 *
 * @param <T> the type of elements this class will read from data storage.
 * @param <K> the type of the key or identifying value to {@code T}.
 */
public interface RODAO<T, K> {

    /**
     * the {@link #getAll()} will get all the class of type <T> form data storage.
     * 
     * @return list of all <T>
     */
    public ArrayList<T> getAll();

    /**
     * {@link #find(K id)}s the first entity {@code T} that have same id in data
     * storage.
     * 
     * @param id {@code K} that it uses to search data storage
     * @return the {@code T} with given {@code K} id in case there are non return
     *         null
     */
    public T find(K id);

}

