package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Tag extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private Integer tagId;

    private String label;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_tag", joinColumns = {@JoinColumn(name = "tagId")}, inverseJoinColumns = {@JoinColumn(name = "postId")})
    private List<Post> posts = new ArrayList<Post>();

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getTagId() {
        return this.tagId;
    }

    public String getLabel() {
        return label;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
