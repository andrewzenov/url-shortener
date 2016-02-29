package by.andrew.zenov.data.dao;

import java.util.List;

public interface Dao<T,K> {

    T get(K id);

    List<T> getAll();

    void delete(K id);

    void insert(T user);

    void update(T user);

}
