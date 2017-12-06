package com.jaxrs.messenger.resources;

import com.jaxrs.messenger.model.Message;
import com.jaxrs.messenger.resources.beans.MessageFilterBean;
import com.jaxrs.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
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
    @Path("/{id : .+}")
    public Message getMessage(@PathParam("id") long id) {
        return service.getMessage(id);
    }

    @POST
    public Response addMessage(Message message) {
        return Response.accepted(service.addMessage(message)).build();
    }

    @PUT
    @Path("/{id : .+}")
    public Response updateMessage(@PathParam("id") long id, Message message) {
        message.setId(id);
        Optional<Message> messageOptional = service.updateMessage(message);
        if (messageOptional.isPresent()) {
            return Response.accepted(messageOptional.get()).build();
        } else {
            return Response.notModified("Recurso no encontrado").build();
        }
    }

    @DELETE
    @Path("/{id : .+}")
    public Response deleteMessage(@PathParam("id") long id) {
        service.deleteMessage(id);
        return Response.noContent().build();
    }

    @Path("/{id}/comments")
    public CommentResource getCommentsResource(@PathParam("id") long id) {
        return new CommentResource();
    }
}
