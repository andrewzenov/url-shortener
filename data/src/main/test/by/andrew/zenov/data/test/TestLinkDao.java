package by.andrew.zenov.data.test;

import by.andrew.zenov.data.TestUtil;
import by.andrew.zenov.data.dao.LinkDao;
import by.andrew.zenov.data.dao.TagDao;
import by.andrew.zenov.data.model.Link;
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
public class TestLinkDao {

    @Autowired
    private LinkDao linkDao;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private TagDao tagDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testInsertLink() {

        Set<Tag> tagSet = testUtil.createTagSet(10);
        for (Tag tag : tagSet) {
            tagDao.insert(tag);
        }

        Set<Tag> tagSetDb = new HashSet<>();
        for (Tag tag : tagDao.getAll()) {
            tagSet.add(tag);
        }

        Link link = testUtil.createLink();
        link.setTags(tagSetDb);

        int startCount = linkDao.getAll().size();

        linkDao.insert(link);
        int endCount = linkDao.getAll().size();

        Assert.assertEquals(1, endCount - startCount);

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetById() {
        Link link = testUtil.createLink();
        linkDao.insert(link);
        Link linkDb = linkDao.getAll().get(0);
        String id = linkDb.getShortUrl();

        Assert.assertEquals(linkDb, linkDao.get(id));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAll() {

        Set<String> shortUrlSet = new HashSet<>();
        int size = testUtil.getRandomInteger(0, 100);

        Link link;
        for (int i = 0; i < size; i++) {
            link = testUtil.createLink();
            if (shortUrlSet.add(link.getShortUrl())) {
                linkDao.insert(link);
            }
        }

        Assert.assertEquals(shortUrlSet.size(), linkDao.getAll().size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateLink() {
        Link link = testUtil.createLink();
        linkDao.insert(link);

        String sourceUrl = testUtil.getRandomString(1000);
        String description = testUtil.getRandomString(10000);
        Long clickCount = (long) testUtil.getRandomInteger(10, 100);

        tagDao.insert(testUtil.createTag());
        Tag tag = tagDao.getAll().get(0);
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(tag);

        Link linkDb = linkDao.getAll().get(0);
        linkDb.setTags(tagSet);
        linkDb.setClickCount(clickCount);
        linkDb.setDescription(description);
        linkDb.setSourceUrl(sourceUrl);

        linkDao.update(linkDb);

        Link linkDb2 = linkDao.get(linkDb.getShortUrl());

        Assert.assertEquals(linkDb2, linkDb);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteUser() {
        Link link = testUtil.createLink();
        linkDao.insert(link);
        int size = linkDao.getAll().size();
        Link linkDb = linkDao.getAll().get(0);
        linkDao.delete(linkDb.getShortUrl());

        Assert.assertEquals(size - 1, linkDao.getAll().size());
    }
}
