package by.andrew.zenov.data.dao;

import by.andrew.zenov.data.model.Link;

import java.util.List;

public interface LinkDao extends Dao<Link,String>{

    public List<Link> getLinksByTag(Long tagId);

}
