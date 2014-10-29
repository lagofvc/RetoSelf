package com.lago.retoself.rest;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.lago.retoself.domain.Category;
import com.lago.retoself.utils.MongoUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@Path("/retosresthome")
public class RetosRestHome {

	public RetosRestHome(){
		try {
			MongoUtils.connectToMongo();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
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
	@Path("/postcategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postCategory(Category cat){
		return MongoUtils.insertCategory(cat);
	}

	@POST
	@Path("/updatecategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCategory(Category cat){
		return MongoUtils.updateCategory(cat);
	}
	
	@DELETE
	@Path("/deletecategory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(Category cat) {
		return MongoUtils.deleteCategory(cat);
	}
}
