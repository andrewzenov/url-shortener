package by.andrew.zenov;

import java.util.List;

import org.springframework.http.ResponseEntity;

import by.andrew.zenov.data.model.Tag;

public interface ITagController {

    List<Tag> getTags();

    Tag getTag(Long id);

    void delete(Long id);

    ResponseEntity<Tag> createTag(Tag tag);


}
