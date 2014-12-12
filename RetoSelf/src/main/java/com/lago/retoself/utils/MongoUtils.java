package com.lago.retoself.utils;

import com.lago.retoself.domain.Category;
import com.lago.retoself.domain.Challenge;
import com.lago.retoself.domain.DomO;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.net.UnknownHostException;
import java.util.List;

public class MongoUtils {

	private static Datastore ds;
	private static final String COLL_DB_NAME = "lagoretos";
	
	public static void connectToMongo() throws UnknownHostException{
		if(ds == null){
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			Morphia morphia = new Morphia();
			morphia.map(Category.class);
            morphia.map(Challenge.class);
			
			ds = morphia.createDatastore(mongoClient, COLL_DB_NAME);
		}
	}

    public static long getCollectionCount(Class classname){
        return ds.getCount(classname);
    }
	
	/**
	 * FIND ALL Categories
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
	public static String insert(DomO domo) {
		ds.save(domo);
		if(domo.getId()!=null && domo.getId().length()>0){
			return "SUCCESS";
		}
		return null;
	}

	/**
	 * INSERT A DomO and return its new ID.
	 * @param DomO domo
	 * @parma boolean returnID
	 * @return
	 */
	public static String insert(DomO domo, boolean returnID) {
		if(returnID == false){
			return insert(domo);
		}
		
		insert(domo);
		return domo.getId();
	}

    public static String update(DomO domo){
        if(domo instanceof Category){
            return updateCategory((Category)domo);
        }

        if(domo instanceof Challenge){
            return updateChallenge((Challenge)domo);
        }

        return "Invalid Domain object";
    }

	/**
	 * UPDATE A Category
	 * @param Category cat
	 * @return
	 */
	public static String updateCategory(Category cat) {
		Query<Category> catToUpdate = ds.find(Category.class, "_id", new ObjectId(cat.getId()));
		Category updatedCat = ds.findAndModify(catToUpdate, ds.createUpdateOperations(Category.class)
                .set("color", cat.getColor())
                .set("name", cat.getName())
        );

        if(updatedCat.getId().toString().equals(cat.getId())){
			return "SUCCESS";
		}
		return "Failed to update";
	}

    /**
     * UPDATE A Category
     * @param Category cat
     * @return
     */
    public static String updateChallenge(Challenge chall) {
        Query<Challenge> challToUpdate = ds.find(Challenge.class, "_id", new ObjectId(chall.getId()));
        UpdateOperations<Challenge> updateOps = ds.createUpdateOperations(Challenge.class)
                .set("name", chall.getName())
                .set("type", chall.getType())
                .set("completed", chall.getCompleted());

        setOtherFieldsIfSetOrUpdated(updateOps, chall);  ///for future optimization.

        Challenge updatedChall = ds.findAndModify(challToUpdate,updateOps);

        if(updatedChall.getId().toString().equals(chall.getId())){
            return "SUCCESS";
        }
        return "Failed to update";
    }

    private static void setOtherFieldsIfSetOrUpdated(UpdateOperations<Challenge> updateOps, Challenge challenge) {
        if(challenge.getCategoryId() != null && !challenge.getCategoryId().isEmpty()){
            updateOps.set("categoryid", challenge.getCategoryId());
        }

        if(challenge.getDescription() != null && !challenge.getDescription().isEmpty()){
            updateOps.set("description",challenge.getDescription());
        }

    }

    /**
	 * DELETE A DomO
     * @param DomO domo
	 * @return
	 */
	public static String delete(DomO domo) {
		Query<DomO> domoToDEL = ds.find(domo.getClassForTableName(), "_id", new ObjectId(domo.getId()));
		
		int deletedCnt = ds.delete(domoToDEL).getN();
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

    /**
     * GET all challenges for all categories
     * @return
     */
    public static List<Challenge> getAllChallenges() {
        return ds.find(Challenge.class).asList();
    }

    /**
     * Get all challenges for a single category by categoryId
     * @param id
     * @return
     */
    public static List<Challenge> getAllChallengesForCategory(String id) {
        Query<Challenge> categoryChallenges = ds.find(Challenge.class, "categoryid", id);
        return categoryChallenges.asList();
    }

    public static Challenge getSingleChallenge(String id) {
        return ds.get(Challenge.class, new ObjectId(id));
    }
}
