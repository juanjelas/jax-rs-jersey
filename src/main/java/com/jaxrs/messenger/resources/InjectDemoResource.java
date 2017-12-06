package com.jaxrs.messenger.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes({MediaType.TEXT_PLAIN})
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public Response getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                              @HeaderParam("customParam") String customString,
                                              @CookieParam("miclave") String cookie) {
        System.out.println("miclave:" + cookie);
        return Response.accepted().cookie(new NewCookie("miclave", "secreta")).build();
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        return uriInfo.getAbsolutePath().toString();
    }
}
