package by.andrew.zenov.rest.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.andrew.zenov.ILinkController;
import by.andrew.zenov.data.model.Link;
import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.service.LinkService;
import by.andrew.zenov.util.RestUtil;

@RestController(value = "linkController")
@RequestMapping("/links")
public class LinkController implements ILinkController {

	@Autowired
	private LinkService linkService;

	@Override
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Link> getLinks() {
		return linkService.getAll();
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Link getLink(String shortUrl) {
		Link link = linkService.get(shortUrl);
		RestUtil.validation(Link.class, link);
		return link;
	}

	@Override
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Link> create(Link link) {
		RestUtil.validation(Link.class, link);
		linkService.insert(link);
		return new ResponseEntity<Link>(link, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Link> update(String shortUrl, Link link) {
		RestUtil.validation(Link.class, link);
		link.setShortUrl(shortUrl);
		linkService.update(link);
		return new ResponseEntity<Link>(link, HttpStatus.OK);
	}

	@Override
	public void delete(String shortUrl) {
		Link link = linkService.get(shortUrl);
		RestUtil.validation(Link.class, link);
		linkService.delete(link);

	}

	@Override
	public Set<Tag> getTags(String shortUrl) {
		Link link = linkService.get(shortUrl);
		RestUtil.validation(Link.class, link);
		return link.getTags();
	}

	@Override
	public ResponseEntity<Link> addTag(String shortUrl, Tag tag) {
		Link link = linkService.get(shortUrl);
		RestUtil.validation(Link.class, link);
		RestUtil.validation(Tag.class, tag);
		link.getTags().add(tag);
		linkService.update(link);
		return new ResponseEntity<Link>(link, HttpStatus.OK);
	}

}
