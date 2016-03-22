package by.andrew.zenov;

import java.util.List;

import org.springframework.http.ResponseEntity;

import by.andrew.zenov.data.model.Tag;

public interface ITagController {
	
	public List<Tag> getTags();
	
	public Tag getTag(Long id);

	public void delete(Long id);

	public ResponseEntity<Tag> createUser(Tag tag);

}
