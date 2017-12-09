package com.jaxrs.messenger.exception;

import com.jaxrs.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage error = new ErrorMessage(e.getMessage(), 404, "www");
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
