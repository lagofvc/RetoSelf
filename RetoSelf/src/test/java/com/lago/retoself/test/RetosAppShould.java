package com.lago.retoself.test;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lago.retoself.domain.Category;
import com.lago.retoself.rest.RetosRestHome;
import com.lago.retoself.utils.MongoUtils;

public class RetosAppShould {
	
	private RetosRestHome rest;
	private static final String VERY_UNIQUE_NAME = "542a391fef86a307f2a13229";
	private static int COUNT_BEFORE = 0;
	private static int COUNT_AFTER = 0;
	
	@Before
	public void setup() throws UnknownHostException{
		rest = new RetosRestHome(); // this sets the connection to MongoDB
		COUNT_BEFORE = MongoUtils.getCollection(Category.TABLENAME).find().size();
		
	}
	
	@After
	public void tearDown() throws UnknownHostException{
		COUNT_AFTER = MongoUtils.getCollection(Category.TABLENAME).find().size();
		if(COUNT_BEFORE < COUNT_AFTER){
			ArrayList<Category> allCats = rest.getCategories();
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
		allowInsertToCategory_test(); //insert dummy category via insertTest
		ArrayList<Category> allCats = rest.getCategories();
		
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
		int before = MongoUtils.getCollection(Category.TABLENAME).find().size();
		
		Category cat = new Category("blue");
		cat.setName(VERY_UNIQUE_NAME);
		rest.postCategory(cat);
		
		int after = MongoUtils.getCollection(Category.TABLENAME).find().size();
		
		assertTrue(before+1 == after);
	}
	
	@Test
	public void allowUpdateACategory_test(){
		Category cat = new Category("blue");
		cat.setName(VERY_UNIQUE_NAME);
		String id = MongoUtils.insertCategory(cat, true);
		cat.setId(id);
		
		cat.setColor("red");
		String result = MongoUtils.updateCategory(cat);
		assertEquals("SUCCESS", result);
		
		Category updatedCategory = MongoUtils.getSingleCategory(id);
		assertEquals("red", updatedCategory.getColor());
	}
}
