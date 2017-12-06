package com.jaxrs.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/comments")
public class CommentResource {

    @GET
    public String getCommentResource() {
        return "new commentResource";
    }

    @GET
    @Path("{pathValue}")
    public String getComments(@PathParam("pathValue") Long value) {
        return "este es tu valor " + value;
    }
}
