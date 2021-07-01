package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class PostTag extends PanacheEntity {
    public Integer postId;
    public Integer tagId;
}
