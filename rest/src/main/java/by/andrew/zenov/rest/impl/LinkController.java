package by.andrew.zenov.rest.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Link getLink(@PathVariable String shortUrl) {
        Link link = linkService.get(shortUrl);
        RestUtil.validation(Link.class, link);
        return link;
    }

    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Link> create(@RequestBody Link link) {
        RestUtil.validation(Link.class, link);
        linkService.insert(link);
        return new ResponseEntity<Link>(link, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Link> update(@PathVariable String shortUrl, @RequestBody Link link) {
        RestUtil.validation(Link.class, link);
        link.setShortUrl(shortUrl);
        linkService.update(link);
        return new ResponseEntity<Link>(link, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void delete(@PathVariable String shortUrl) {
        Link link = linkService.get(shortUrl);
        RestUtil.validation(Link.class, link);
        linkService.delete(link);

    }

    @Override
    @RequestMapping(value = "/{shortUrl}/tags", method = RequestMethod.GET, headers = "Accept=application/json")
    public Set<Tag> getTags(@PathVariable String shortUrl) {
        Link link = linkService.get(shortUrl);
        RestUtil.validation(Link.class, link);
        return link.getTags();
    }

    @Override
    @RequestMapping(value = "/{shortUrl}/tags", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Link> addTag(@PathVariable String shortUrl, @RequestBody Tag tag) {
        Link link = linkService.get(shortUrl);
        RestUtil.validation(Link.class, link);
        RestUtil.validation(Tag.class, tag);
        link.getTags().add(tag);
        linkService.update(link);
        return new ResponseEntity<Link>(link, HttpStatus.OK);
    }

}