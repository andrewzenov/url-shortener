package by.andrew.zenov.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.andrew.zenov.ITagController;
import by.andrew.zenov.data.model.Tag;
import by.andrew.zenov.service.TagService;
import by.andrew.zenov.util.RestUtil;

@RestController(value = "tagController")
@RequestMapping("/tags")
public class TagController implements ITagController {

	@Autowired
	private TagService tagService;

	@Override
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Tag> getTags() {
		return tagService.getAll();
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Tag getTag(@PathVariable Long id) {
		Tag tag = tagService.get(id);
		RestUtil.validation(Tag.class, tag);
		return tag;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable Long id) {
		Tag tag = tagService.get(id);
		RestUtil.validation(Tag.class, tag);
		tagService.delete(tag);
	}

	@Override
	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
		RestUtil.validation(Tag.class, tag);
		tag.setId(null);
		tagService.insert(tag);
		return new ResponseEntity<Tag>(tag, HttpStatus.OK);
	}

}
