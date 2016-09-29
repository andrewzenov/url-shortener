package utils;

import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.data.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Андрей on 28.09.2016.
 */
public class TestUtils {

    /**
     * @param id
     * @return Tag with next fields:
     * id:{id};
     * title: 'tag_{id}_title'.
     */
    public static Tag createTag(Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setTitle(String.format("tag_%s_title", id));
        return tag;
    }

    /**
     * @param id
     * @return User with next fields:
     * id:{id};
     * login:'user_{id}';
     * password:'password_{id}';
     * email:'email_{id}'
     * links:this is HashSet with one link which has fields:
     * shortUrl:'link_user_{id}';
     * description:'link of user_{id}'.
     */
    public static User createUser(Long id) {
        User user = new User();

        user.setId(id);
        user.setLogin(String.format("user_%s", id));
        user.setPassword(String.format("password_%s", id));
        user.setEmail(String.format("email_%s", id));

        Link link = new Link();
        link.setShortUrl(String.format("link_user_%s", id));
        link.setDescription(String.format("link of user_%s", id));

        user.setLinks(new HashSet<>(Arrays.asList(link)));

        return user;
    }

    /**
     *
     * @param shortUrl - id for link
     * @param userId - link's user id
     * @param tagId - link's tag id for Set<Tag>
     * @return Link with next fields:
     * shortUrl:{shortUrl};
     * sourceUrl:'source for {shortUrl}';
     * description: 'description of {shortUrl}';
     * clickCount: random number after 0 before 100;
     * user: Object User with id {userId}
     * tags: set with once element Tag with id {tagId}
     */
    public static Link createLink(String shortUrl, Long userId, Long tagId) {
        Link link = new Link();
        link.setShortUrl(shortUrl);
        link.setSourceUrl(String.format("source for %s", shortUrl));
        link.setDescription(String.format("description of %s", shortUrl));
        link.setClickCount((long) (Math.random() * 100));

        User user = createUser(userId);
        Set<Tag> tags = new HashSet<>();
        tags.add(createTag(tagId));

        link.setUser(user);
        link.setTags(tags);

        return link;
    }
}
