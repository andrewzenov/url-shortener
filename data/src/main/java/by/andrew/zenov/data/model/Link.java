package by.andrew.zenov.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "short_url", length = 10)
    private String shortUrl;

    @Column(name = "source_url", nullable = false, length = 1000)
    private String sourceUrl;

    @Column(name = "description", nullable = true, length = 10000)
    private String description;

    @Column(name = "click_count", nullable = true)
    private Long clickCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_link", joinColumns = @JoinColumn(name = "short_url"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return com.google.common.base.Objects.equal(shortUrl, link.shortUrl) &&
                com.google.common.base.Objects.equal(sourceUrl, link.sourceUrl) &&
                com.google.common.base.Objects.equal(description, link.description) &&
                com.google.common.base.Objects.equal(clickCount, link.clickCount) &&
                com.google.common.base.Objects.equal(tags, link.tags);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(shortUrl, sourceUrl, description, clickCount, tags);
    }
}