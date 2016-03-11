package by.andrew.zenov.data.dao;

import java.util.List;

public interface Dao<T,K> {

    T get(K id);

    List<T> getAll();

    void delete(T entity);

    void insert(T entity);

    void update(T entity);

}
