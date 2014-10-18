package com.lago.retoself.utils;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.lago.retoself.domain.Category;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoUtils {

	private static DB db;
	private static DBCollection coll;
	
	public static void connectToMongo() throws UnknownHostException{
		if(db == null){
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			db = mongoClient.getDB("lagoretos");
		}
	}
	
	public static void setCollection(String collection){
		coll = db.getCollection(collection);
	}
	
	public static DBCollection getCollection(String collection){
		coll = db.getCollection(collection);
		return coll;
	}
	
	public static DBCursor getAll(){
		return coll.find();
	}
	
	/**
	 * FIND ALL Categorys
	 * @return
	 * @throws UnknownHostException
	 */
	public static ArrayList<Category> getAllCategories() {
		ArrayList<Category> categories = new ArrayList<Category>();
		
		MongoUtils.setCollection("category");
		DBCollection categoryColl = MongoUtils.getCollection(Category.TABLENAME);
		DBCursor cursor = categoryColl.find();
		try{
			while(cursor.hasNext()) {
				DBObject obj = cursor.next();
				Category objRet = new Category();
				objRet.setColor((String)obj.get("color"));
				objRet.setId(((ObjectId)obj.get("_id")).toString());
				objRet.setName((String)obj.get("name"));
				categories.add(objRet);
			}
		}finally{
			cursor.close();
		}
		return categories;
	}

	/**
	 * INSERT A Category
	 * @param Category cat
	 * @return
	 */
	public static String insertCategory(Category cat) {
		MongoUtils.setCollection(Category.TABLENAME);
		DBCollection coll = MongoUtils.getCollection(Category.TABLENAME);
		
		BasicDBObject category = new BasicDBObject("name",cat.getName());
		category.append("color", cat.getColor());
		try{
			coll.insert(category);
			ObjectId objId = (ObjectId)category.get( "_id" );
			if(objId!=null){
				return "SUCCESS";
			}
		}catch(MongoException moe){
			return moe.getMessage();
		}
		return null;
	}
	
	/**
	 * INSERT A Category and return its new ID.
	 * @param Category cat
	 * @parma boolean returnID
	 * @return
	 */
	public static String insertCategory(Category cat, boolean returnID) {
		if(returnID == false){
			return insertCategory(cat);
		}
		
		MongoUtils.setCollection(Category.TABLENAME);
		DBCollection coll = MongoUtils.getCollection(Category.TABLENAME);
		
		BasicDBObject category = new BasicDBObject("name",cat.getName());
		category.append("color", cat.getColor());
		try{
			coll.insert(category);
			ObjectId objId = (ObjectId)category.get( "_id" );
			if(objId!=null){
				return objId.toString(); //return the ID.
			}
		}catch(MongoException moe){
			return moe.getMessage();
		}
		return null;
	}

	/**
	 * UPDATE A Category
	 * @param Category cat
	 * @return
	 */
	public static String updateCategory(Category cat) {
		DBCollection coll = MongoUtils.getCollection(Category.TABLENAME);
		BasicDBObject category = new BasicDBObject("_id", new ObjectId(cat.getId()));
		category.append("name",cat.getName());
		category.append("color", cat.getColor());
		try{
			coll.save(category);
			ObjectId objId = (ObjectId)category.get( "_id" );
			if(objId!=null && objId.toString().equals(cat.getId())){
				return "SUCCESS";
			}
		}catch(MongoException moe){
			return moe.getMessage();
		}
		return null;
	}
	
	/**
	 * DELETE A Category
	 * @param Category cat
	 * @return
	 */
	public static String deleteCategory(Category cat) {
		MongoUtils.setCollection(Category.TABLENAME);
		DBCollection coll = MongoUtils.getCollection(Category.TABLENAME);
		try{
			coll.remove(new BasicDBObject("_id", new ObjectId(cat.getId())));
			return "SUCCESS";
		}catch(MongoException moe){
			return moe.getMessage();
		}
	}

	/**
	 * GET SINGLE Category
	 * @param String id
	 * @return
	 */
	public static Category getSingleCategory(String id) {
		DBCollection categoryColl = MongoUtils.getCollection(Category.TABLENAME);
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		DBCursor cursor = categoryColl.find(query);
		Category categoryToReturn = null;
		try{
			if(cursor.size() == 1) {
				DBObject obj = cursor.next();
				categoryToReturn = new Category();
				categoryToReturn.setColor((String)obj.get("color"));
				categoryToReturn.setId(((ObjectId)obj.get("_id")).toString());
				categoryToReturn.setName((String)obj.get("name"));
			}else{
			}
		}finally{
			cursor.close();
		}
		return categoryToReturn;
	}
	
}
