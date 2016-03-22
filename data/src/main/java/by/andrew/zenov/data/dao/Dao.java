package by.andrew.zenov.data.dao;

import java.util.List;
import java.util.Map;

public interface Dao<T,K> {

    T get(K id);
    
    T get(K id, Map<String,Object> properties);

    List<T> getAll();

    void delete(K id);

    void insert(T entity);

    void update(T entity);

}
