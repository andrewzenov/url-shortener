package by.andrew.zenov.service;

import by.andrew.zenov.data.model.Tag;

import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */
public interface TagService {

    Tag get(Long id);

    List<Tag> getAll();

    void delete(Long id);
    
    void delete(Tag tag);

    void insert(Tag tag);

    void update(Tag tag);
}
