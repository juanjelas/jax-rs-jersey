package com.jaxrs.messenger.resources;

import com.jaxrs.messenger.model.Message;
import com.jaxrs.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class MessageResource {

    MessageService service = new MessageService();

    @GET
    public List<Message> getMessage(@QueryParam("year") int year,
                                    @QueryParam("start") int start,
                                    @QueryParam("size") int size) {
        if (year > 0) {
            return service.getAllMessageForYear(year);
        }
        if (start > 0 && size > 0) {
            return service.getAllMessagePaginated(start, size);
        }
        return service.getAllMessages();
    }

    @GET
    @Path("{id : .+}")
    public Message getMessage(@PathParam("id") long id) {
        return service.getMessage(id);
    }

    @POST
    public Response addMessage(Message message) {
        return Response.accepted(service.addMessage(message)).build();
    }

    @PUT
    @Path("{id : .+}")
    public Response updateMessage(@PathParam("id") long id, Message message) {
        message.setId(id);
        return Response.accepted(service.updateMessage(message)).build();
    }

    @DELETE
    @Path("{id : .+}")
    public Response deleteMessage(@PathParam("id") long id) {
        service.deleteMessage(id);
        return Response.noContent().build();
    }
}
