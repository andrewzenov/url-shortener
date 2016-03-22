package by.andrew.zenov;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.Tag;

public interface ILinkController {

	public List<Link> getLinks();

	public Link getLink(String shortUrl);

	public ResponseEntity<Link> create(Link link);

	public ResponseEntity<Link> update(String shortUrl, Link link);

	public void delete(String shortUrl);

	public Set<Tag> getTags(String shortUrl);

	public ResponseEntity<Link> addTag(String shortUrl, Tag tag);

}
