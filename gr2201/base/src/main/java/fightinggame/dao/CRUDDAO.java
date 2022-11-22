package fightinggame.dao;

import java.util.ArrayList;

/**
 * The {@link CRUDDAO} class (Create Read Update Delete Data Access Object) is
 * following the Data Access Object pattern.
 * The interface shall make it possible to interact with the data storage system
 * without knowing the implementation.
 * 
 * @param <T> The type of the object to access
 * @param <K> The type of the primary key of the object to access
 */
public interface CRUDDAO<T, K> {

    /**
     * The {@link #loadAll} will get all instances of the class of type <T> form
     * data storage.
     * 
     * @return list of all <T>
     */
    public ArrayList<T> loadAll();

    /**
     * Finds the {@code T} that have same id in data storage.
     * 
     * @param id {@code K} that it uses to search data storage
     * @return the {@code T} with given {@code K} id in case there are non return
     *         null
     */
    public T get(K id);

    /**
     * The method {@link #update(K id, T object)}s the user with this {@code id} and
     * changes the info into given {@code data}
     * If there are no user with userId in data storage do nothing.
     * 
     * @param id     that is used for search
     * @param object to change to
     */
    public boolean update(K id, T object);

    /**
     * The method {@link #delete(K id)} the T with given {@code id}.
     * If there are no user with userId in data storage do nothing.
     * 
     * @param id that is used for search
     */
    public boolean delete(K id);

    /**
     * The method {@link #add(T object)} adds T to data storage.
     * If there already exist a T with same id do nothing.
     * 
     * @param id is search value and added if not already present add object
     */
    public boolean add(T object);
}
