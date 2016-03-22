package by.andrew.zenov.data;

import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.data.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Андрей on 29.02.2016.
 */
@Service(value = "testUtil")
public class TestUtil {

    public String getRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public int getRandomInteger(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        return result;
    }

    public User createUser() {
        User user = new User();
        user.setLogin(getRandomString(20));
        user.setPassword(getRandomString(40));
        user.setEmail(getRandomString(60));
        return user;
    }

    public Tag createTag() {
        Tag tag = new Tag();
        tag.setTitle(getRandomString(40));
        return tag;
    }

    public Set<Tag> createTagSet(int size) {
        Set<Tag> result = new HashSet<>();
        Set<String> titles = new HashSet<>();
        Tag tag;
        for (int i = 0; i < size; i++) {
            tag = createTag();
            if (titles.add(tag.getTitle())) {
                result.add(tag);
            }
        }
        return result;
    }

    public Link createLink() {
        Link link = new Link();
        link.setShortUrl(getRandomString(10));
        link.setSourceUrl(getRandomString(1000));
        link.setDescription(getRandomString(10000));
        link.setClickCount((long) getRandomInteger(0, 1000));
        return link;
    }
}
