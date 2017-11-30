package com.jaxrs.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes({MediaType.TEXT_PLAIN})
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String test(@MatrixParam("param") String matrixParam,
                       @HeaderParam("customParam") String customString,
                       @CookieParam("name") String cookie) {
        return "valor param: " + matrixParam + " customParam: " + customString + " cookieParam: " + cookie;
    }
}
