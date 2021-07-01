package com.example;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tags")
public class TagResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTag() {
        List<Tag> tag = Tag.listAll();
        return Response.ok(tag).build();
    }

    @Path("/{id}")
    @GET
    public Response getTag(@PathParam("id") Integer id) {
        Tag tag = Tag.findById(id);
        return Response.ok(tag).build();
    }

    @POST
    @Transactional
    public Response createTag(Tag tag) {
        tag.persist();
        return Response.ok(tag).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteTag(@PathParam("id") Integer id) {
        boolean tag = Tag.deleteById(id);
        if (tag) {
            return Response.ok("successfully delete").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response updateTag(@PathParam("id") Integer id, Tag tag) {
        Tag.update("label = ?1 where id = ?2", tag.getLabel(), id);
        Tag tagCurrent = Tag.findById(id);
        return Response.ok(tagCurrent).build();
    }
}
