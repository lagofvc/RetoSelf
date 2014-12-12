package com.lago.retoself.test;

import com.lago.retoself.domain.Category;
import com.lago.retoself.domain.Challenge;
import com.lago.retoself.rest.RetosRestHome;
import com.lago.retoself.utils.MongoUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ChallengeAppShould {
	
	private RetosRestHome rest;
	public static final String VERY_UNIQUE_NAME = "542a391fef86a307f2a13229";
	private static long CATEGORY_COUNT_BEFORE = 0;
	private static long CATEGORY_COUNT_AFTER = 0;
    private static long CHALLENGE_COUNT_BEFORE = 0;
    private static long CHALLENGE_COUNT_AFTER = 0;
	
	@Before
	public void setup() throws UnknownHostException{
		rest = new RetosRestHome(); // this sets the connection to MongoDB
		CATEGORY_COUNT_BEFORE = MongoUtils.getCollectionCount(Category.class);
        CHALLENGE_COUNT_BEFORE = MongoUtils.getCollectionCount(Challenge.class);
		
	}
	
	@After
	public void tearDown() throws UnknownHostException{
		CATEGORY_COUNT_AFTER = MongoUtils.getCollectionCount(Category.class);
		if(CATEGORY_COUNT_BEFORE < CATEGORY_COUNT_AFTER){
			List<Category> allCats = rest.getCategories();
			for(Category cat : allCats){
				if(cat.getName().equals(VERY_UNIQUE_NAME)){
					rest.delete(cat);
				}
			}
		}

        CHALLENGE_COUNT_AFTER = MongoUtils.getCollectionCount(Challenge.class);
        if(CHALLENGE_COUNT_BEFORE < CHALLENGE_COUNT_AFTER){
            List<Challenge> allChalls = MongoUtils.getAllChallenges();
            for(Challenge chall : allChalls){
                if(chall.getName().equals(VERY_UNIQUE_NAME)){
                    rest.delete(chall);
                }
            }
        }

		rest = null;


	}
	
	@Test
	public void allowDeleteOnCategory_test(){
		
		Category cat1 = new Category("blue");
		cat1.setName(VERY_UNIQUE_NAME);
		rest.addCategory(cat1);
		
		List<Category> allCats = rest.getCategories();
		
		String result = null;
		for(Category cat : allCats){
			if(cat.getName().equals(VERY_UNIQUE_NAME)){
				result = rest.delete(cat);
				break;
			}
		}
		assertTrue(result.equals("SUCCESS"));
	}
	
	@Test
	public void allowInsertToCategory_test(){
		long before = MongoUtils.getCollectionCount(Category.class);
		
		Category cat = new Category("blue");
		cat.setName(VERY_UNIQUE_NAME);
		rest.addCategory(cat);
		
		long after = MongoUtils.getCollectionCount(Category.class);
		
		assertTrue(before+1 == after);
	}
	
	@Test
	public void allowUpdateACategory_test(){
		Category cat = new Category("blue");
		cat.setName(VERY_UNIQUE_NAME);
		String id = MongoUtils.insert(cat, true);
		
		cat.setColor("red");
		String result = MongoUtils.updateCategory(cat);
		assertEquals("SUCCESS", result);
		
		Category updatedCategory = MongoUtils.getSingleCategory(id);
		assertEquals("red", updatedCategory.getColor());
	}

    @Test
    public void allowInsertToChallenge_test(){
        long before = MongoUtils.getCollectionCount(Challenge.class);

        Challenge chall = new Challenge();
        chall.setName(VERY_UNIQUE_NAME);
        rest.addChallenge(chall);

        long after = MongoUtils.getCollectionCount(Challenge.class);

        assertTrue(before + 1 == after);
    }

    @Test
    public void allowUpdateAChallenge_test(){
        Challenge chall = new Challenge();
        chall.setName(VERY_UNIQUE_NAME);
        String id = MongoUtils.insert(chall, true);

        chall.setCompleted(true);
        String result = MongoUtils.updateChallenge(chall);
        assertEquals("SUCCESS", result);

        Challenge updatedChallenge = MongoUtils.getSingleChallenge(id);
        assertTrue(updatedChallenge.getCompleted().booleanValue());
    }

    @Test
    public void allowDeleteOnChallenge_test(){

        Challenge challenge = new Challenge();
        challenge.setName(VERY_UNIQUE_NAME);
        challenge.setCompleted(true);
        rest.addChallenge(challenge);

        List<Challenge> allChallenges = MongoUtils.getAllChallenges();

        String result = null;
        for(Challenge chall : allChallenges){
            if(chall.getName().equals(VERY_UNIQUE_NAME)){
                result = rest.delete(chall);
                break;
            }
        }
        assertTrue(result.equals("SUCCESS"));
    }

    @Test
    public void allowChallengeToBeLinkedToCategory_test() {
        Challenge challenge = new Challenge();
        challenge.setName(VERY_UNIQUE_NAME);
        challenge.setDescription("Lose 3 lbs.");
        challenge.setCompleted(false);
        String id = MongoUtils.insert(challenge, true);

        challenge.setCategoryId(VERY_UNIQUE_NAME);
        MongoUtils.update(challenge);

        Challenge updatedChall = MongoUtils.getSingleChallenge(id);
        assertEquals(updatedChall.getCategoryId(), VERY_UNIQUE_NAME);
    }

    @Test
    public void shouldReturnAListOfChallengeTypes_test(){
        assertNotNull(rest.getChallengeTypes());
    }
}
