package by.andrew.zenov.service.impl;

import by.andrew.zenov.data.dao.TagDao;
import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Андрей on 10.03.2016.
 */
@Service(value = "tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Tag get(Long id) {
        return tagDao.get(id);
    }

    @Override
    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagDao.delete(id);
    }
    
    @Override
    @Transactional
    public void delete(Tag tag) {
        tagDao.delete(tag.getId());
    }

    @Override
    @Transactional
    public void insert(Tag tag) {
        tagDao.insert(tag);
    }

    @Override
    @Transactional
    public void update(Tag tag) {
        tagDao.update(tag);
    }

}
