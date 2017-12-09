package com.jaxrs.messenger.exception;

import com.jaxrs.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        ErrorMessage error = new ErrorMessage(e.getMessage(), 500, "www");
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
