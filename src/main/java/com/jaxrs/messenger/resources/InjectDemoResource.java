package com.jaxrs.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes({MediaType.TEXT_PLAIN})
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("customParam") String customString,
                                            @CookieParam("name") String cookie) {
        return "valor param: " + matrixParam + " customParam: " + customString + " cookieParam: " + cookie;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        return uriInfo.getAbsolutePath().toString();
    }
}
