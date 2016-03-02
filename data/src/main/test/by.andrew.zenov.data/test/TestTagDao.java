package by.andrew.zenov.data.test;

import by.andrew.zenov.data.TestUtil;
import by.andrew.zenov.data.dao.TagDao;
import by.andrew.zenov.data.model.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@ContextConfiguration(locations = "classpath:data-spring-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTagDao {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TestUtil testUtil;

    @Test
    @Transactional
    @Rollback(true)
    public void testInsertTag() {
        Tag tag = testUtil.createTag();

        int startCount = tagDao.getAll().size();
        tagDao.insert(tag);
        int endCount = tagDao.getAll().size();

        Assert.assertEquals(1, endCount - startCount);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetById() {
        Tag tag = testUtil.createTag();
        tagDao.insert(tag);
        Tag tagDb = tagDao.getAll().get(0);
        Long id = tagDb.getId();

        Assert.assertEquals(tagDb, tagDao.get(id));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() {

        Set<String> titleSet = new HashSet<>();
        int size = testUtil.getRandomInteger(0, 100);

        Tag tag;
        for (int i = 0; i < size; i++) {
            tag = testUtil.createTag();
            if (titleSet.add(tag.getTitle())) {
                tagDao.insert(tag);
            }
        }

        Assert.assertEquals(titleSet.size(), tagDao.getAll().size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateTag() {
        Tag tag = testUtil.createTag();
        tagDao.insert(tag);

        String title = testUtil.getRandomString(40);

        Tag tagDb = tagDao.getAll().get(0);
        tagDb.setTitle(title);

        tagDao.update(tagDb);

        Tag tagDb2 = tagDao.getAll().get(0);

        Assert.assertEquals(tagDb, tagDb2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteTag() {
        Tag tag = testUtil.createTag();
        tagDao.insert(tag);
        int size = tagDao.getAll().size();
        Tag tagDb = tagDao.getAll().get(0);
        tagDao.delete(tagDb);

        Assert.assertEquals(size - 1, tagDao.getAll().size());
    }

}

