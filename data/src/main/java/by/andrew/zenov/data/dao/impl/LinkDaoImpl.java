package by.andrew.zenov.data.dao.impl;

import by.andrew.zenov.data.model.Link;
import org.springframework.stereotype.Repository;

import by.andrew.zenov.data.dao.LinkDao;

@Repository(value = "linkDao")
public class LinkDaoImpl extends AbstractDao<Link, String> implements LinkDao {

    public LinkDaoImpl() {
        setEntityClass(Link.class);
    }

}
