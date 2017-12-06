package com.jaxrs.messenger.resources;

import com.jaxrs.messenger.model.Comment;
import com.jaxrs.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @POST
    public Response addComment(@PathParam("messageId") long messageId, Comment comment, @Context UriInfo uriInfo) {
        Comment newComment = commentService.addComment(messageId, comment);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newComment.getId())).build();
        return Response.created(uri).entity(newComment).build();
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        commentService.deleteComment(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Response getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        Optional<Comment> commentOptional = commentService.getComment(messageId, commentId);
        if (commentOptional.isPresent()) {
            return Response.accepted(commentOptional.get()).build();
        } else {
            return Response.noContent().build();
        }
    }
}
