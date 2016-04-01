package by.andrew.zenov.data.dao.impl;

import by.andrew.zenov.data.dao.LinkDao;
import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "linkDao")
public class LinkDaoImpl extends AbstractDao<Link, String> implements LinkDao {


    public LinkDaoImpl() {
        setEntityClass(Link.class);
    }

    @Override
    public List<Link> getLinksByTag(Long tagId) {

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Link> criteriaQuery = criteriaBuilder
                .createQuery(Link.class);
        Root<Link> linkRoot = criteriaQuery.from(Link.class);
        Join<Link, Tag> tags = linkRoot.join("tags");
        criteriaQuery.where(criteriaBuilder.equal(tags.get("id"),
                tagId));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }
}
