package com.jaxrs.messenger.resources;

import com.jaxrs.messenger.model.Profile;
import com.jaxrs.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON})
public class ProfileResource {

    ProfileService service = new ProfileService();

    @GET
    public List<Profile> getMessage() {
        return service.getAllProfiles();
    }

    @GET
    @Path("{profile : .+}")
    public Profile getMessage(@PathParam("profile") String profile) {
        return service.getProfile(profile);
    }

    @POST
    public Response addMessage(Profile profile) {
        return Response.accepted(service.addProfile(profile)).build();
    }

    @PUT
    @Path("{profile : .+}")
    public Response updateMessage(@PathParam("profile") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return Response.accepted(service.updateProfile(profile)).build();
    }

    @DELETE
    @Path("{profile : .+}")
    public Response deleteMessage(@PathParam("profile") String profile) {
        service.deleteProfile(profile);
        return Response.noContent().build();
    }
}
