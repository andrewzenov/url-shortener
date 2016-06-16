package by.andrew.zenov;

import java.util.List;
import java.util.Set;

import by.andrew.zenov.data.model.User;
import org.springframework.http.ResponseEntity;

import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.Tag;

public interface ILinkController {

    public List<Link> getLinks();

    public List<Link> getLinksByTag(Long tagId);

    public Link getLink(String shortUrl);

    public User getUser(String shortUrl);

    public ResponseEntity<Link> createLink(Link link);

    public ResponseEntity<Link> update(String shortUrl, Link link);

    public void delete(String shortUrl);

    public Set<Tag> getTags(String shortUrl);

    public ResponseEntity<Set<Tag>> addTag(String shortUrl, Tag tag);

    public ResponseEntity<Set<Tag>> deleteTag(String shortUrl, Tag tag);

}
