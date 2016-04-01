package by.andrew.zenov.service;

import by.andrew.zenov.data.model.Link;

import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */
public interface LinkService {

    Link get(String shortUrl);

    List<Link> getAll();

    List<Link> getLinksByTag(Long tagId);

    void delete(String shortUrl);
    
    void delete(Link link);

    void insert(Link link);

    void update(Link link);
}
