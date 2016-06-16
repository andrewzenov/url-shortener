package by.andrew.zenov.data.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false, length = 40)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equal(id, tag.id) &&
                Objects.equal(title, tag.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
