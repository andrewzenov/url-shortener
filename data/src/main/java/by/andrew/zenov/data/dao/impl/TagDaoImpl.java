package by.andrew.zenov.data.dao.impl;

import by.andrew.zenov.data.dao.TagDao;
import by.andrew.zenov.data.model.Tag;
import org.springframework.stereotype.Repository;

@Repository(value = "tagDao")
public class TagDaoImpl extends AbstractDao<Tag, Long> implements TagDao {

    public TagDaoImpl() {
        setEntityClass(Tag.class);
    }

}
