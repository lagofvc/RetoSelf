package com.lago.retoself.utils;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.lago.retoself.domain.Category;
import com.mongodb.MongoClient;

public class MongoUtils {

	private static Datastore ds;
	private static final String COLL_DB_NAME = "lagoretos";
	
	public static void connectToMongo() throws UnknownHostException{
		if(ds == null){
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			Morphia morphia = new Morphia();
			morphia.map(Category.class);
			
			ds = morphia.createDatastore(mongoClient, COLL_DB_NAME);
		}
	}

	public static long getCollectionCount(){
		return ds.getCount(Category.class);
	}
	
	/**
	 * FIND ALL Categorys
	 * @return
	 * @throws UnknownHostException
	 */
	public static List<Category> getAllCategories() {
		return ds.find(Category.class).asList();
	}

	/**
	 * INSERT A Category
	 * @param Category cat
	 * @return
	 */
	public static String insertCategory(Category cat) {
		ds.save(cat);
		if(cat.getId()!=null && cat.getId().length()>0){
			return "SUCCESS";
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
		
		insertCategory(cat);
		return cat.getId();
	}

	/**
	 * UPDATE A Category
	 * @param Category cat
	 * @return
	 */
	public static String updateCategory(Category cat) {
		Query<Category> catToUpdate = ds.find(Category.class, "_id", new ObjectId(cat.getId()));
		Category updatedCat = ds.findAndModify(catToUpdate, ds.createUpdateOperations(Category.class).set("color",cat.getColor()).set("name", cat.getName()));
		if(updatedCat.getId().toString().equals(cat.getId())){
			return "SUCCESS";
		}
		return "Failed to update";
	}
	
	/**
	 * DELETE A Category
	 * @param Category cat
	 * @return
	 */
	public static String deleteCategory(Category cat) {
		Query<Category> catToDEL = ds.find(Category.class, "_id", new ObjectId(cat.getId()));
		
		int deletedCnt = ds.delete(catToDEL).getN(); 
		if(deletedCnt == 1){
			return "SUCCESS";
		}
		else return "Failed to delete";
	}

	/**
	 * GET SINGLE Category
	 * @param String id
	 * @return
	 */
	public static Category getSingleCategory(String id) {
		return ds.get(Category.class, new ObjectId(id));
	}
	
}
