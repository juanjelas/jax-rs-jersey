package com.jaxrs.messenger.resources;

import com.jaxrs.messenger.model.Message;
import com.jaxrs.messenger.resources.beans.MessageFilterBean;
import com.jaxrs.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("messages")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

    MessageService service = new MessageService();

    @GET
    public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return service.getAllMessageForYear(filterBean.getYear());
        }
        if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
            return service.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
        }
        return service.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        Message message = service.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentsResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId()).build().toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
    }


    private String getUriForSelf(@Context UriInfo uriInfo, final Message message) {
        return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build().toString();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = service.addMessage(message);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
        return Response.created(uri).entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        Optional<Message> messageOptional = service.updateMessage(message);
        if (messageOptional.isPresent()) {
            return Response.accepted(messageOptional.get()).build();
        } else {
            return Response.notModified("Recurso no encontrado").build();
        }
    }

    @DELETE
    @Path("/{messageId}")
    public Response deleteMessage(@PathParam("messageId") long id) {
        service.deleteMessage(id);
        return Response.noContent().build();
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentsResource(@PathParam("messageId") long id) {
        return new CommentResource();
    }
}
