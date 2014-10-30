package com.lago.retoself.test;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lago.retoself.domain.Category;
import com.lago.retoself.rest.RetosRestHome;
import com.lago.retoself.utils.MongoUtils;

public class RetosAppShould {
	
	private RetosRestHome rest;
	private static final String VERY_UNIQUE_NAME = "542a391fef86a307f2a13229";
	private static long COUNT_BEFORE = 0;
	private static long COUNT_AFTER = 0;
	
	@Before
	public void setup() throws UnknownHostException{
		rest = new RetosRestHome(); // this sets the connection to MongoDB
		COUNT_BEFORE = MongoUtils.getCollectionCount();
		
	}
	
	@After
	public void tearDown() throws UnknownHostException{
		COUNT_AFTER = MongoUtils.getCollectionCount();
		if(COUNT_BEFORE < COUNT_AFTER){
			List<Category> allCats = rest.getCategories();
			for(Category cat : allCats){
				if(cat.getName().equals(VERY_UNIQUE_NAME)){
					rest.delete(cat);
				}
			}
		}
		rest = null;
	}
	
	@Test
	public void allowDeleteOnCategory_test(){
		
		Category cat1 = new Category("blue");
		cat1.setName(VERY_UNIQUE_NAME);
		rest.postCategory(cat1);
		
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
		long before = MongoUtils.getCollectionCount();
		
		Category cat = new Category("blue");
		cat.setName(VERY_UNIQUE_NAME);
		rest.postCategory(cat);
		
		long after = MongoUtils.getCollectionCount();
		
		assertTrue(before+1 == after);
	}
	
	@Test
	public void allowUpdateACategory_test(){
		Category cat = new Category("blue");
		cat.setName(VERY_UNIQUE_NAME);
		String id = MongoUtils.insertCategory(cat, true);
		
		cat.setColor("red");
		String result = MongoUtils.updateCategory(cat);
		assertEquals("SUCCESS", result);
		
		Category updatedCategory = MongoUtils.getSingleCategory(id);
		assertEquals("red", updatedCategory.getColor());
	}
}
