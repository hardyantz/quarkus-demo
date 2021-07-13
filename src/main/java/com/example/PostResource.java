package com.example;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/posts")
public class PostResource {

    @GET
    public Response listPost() {
        List<Post> post = Post.listAll();
        return Response.ok(post).build();
    }

    @Path("/{id}")
    @GET
    public Response getPost(@PathParam("id") Integer id) {
        Post post = Post.findById(id);
        return Response.ok(post).build();
    }

    @POST
    @Transactional
    public Response createPost(Post post) {
        post.persist();
        return Response.ok(post).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deletePost(@PathParam("id") Integer id) {
        boolean post = Post.deleteById(id);
        if (post) {
            return Response.ok("successfully delete").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response updatePost(@PathParam("id") Integer id, Post post) {
        Post postCurrent = Post.findById(id);
        postCurrent.setPostId(id);
        postCurrent.setTitle(post.getTitle());
        postCurrent.setContent(post.getContent());
        postCurrent.setTags(post.getTags());
        Post.persist(postCurrent);
        return Response.ok(postCurrent).build();
    }
}
