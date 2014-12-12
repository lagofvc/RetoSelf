package com.lago.retoself.rest;

import com.lago.retoself.domain.Category;
import com.lago.retoself.domain.Challenge;
import com.lago.retoself.utils.MongoUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.List;

@Path("/retosresthome")
public class RetosRestHome {

	public RetosRestHome(){
		try {
			MongoUtils.connectToMongo();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

    /********* CATEGORY REST ********/

	@GET
	@Path("/getcategories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategories(){
		List<Category> cats = MongoUtils.getAllCategories();
		return cats;
	}
	
	@POST
	@Path("/getsinglecategory")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Category getSingleCategory(String id){
		return MongoUtils.getSingleCategory(id);
	}
	
	@POST
	@Path("/addcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(Category cat){
		return MongoUtils.insert(cat);
	}

	@POST
	@Path("/updatecategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update(Category cat){
		return MongoUtils.update(cat);
	}
	
	@DELETE
	@Path("/deletecategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(Category cat) {
		return MongoUtils.delete(cat);
	}

    /********* CHALLENGE REST ********/

    @POST
    @Path("/getchallenges")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Challenge> getChallenges(String id) {
        List<Challenge> challs = MongoUtils.getAllChallengesForCategory(id);
        return challs;
    }

    @POST
    @Path("/addchallenge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addChallenge(Challenge chall){
        return MongoUtils.insert(chall);
    }

    @DELETE
    @Path("/deletechallenge")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(Challenge chall) {
        return MongoUtils.delete(chall);
    }

    @GET
    @Path("/getchallengetypes")
    @Produces(MediaType.TEXT_PLAIN)
    public String getChallengeTypes() {
        return Challenge.ChallengeType.getTypes();
    }
}
