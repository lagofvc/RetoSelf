package com.lago.retoself.test;

import com.lago.retoself.domain.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CategoryShould {

	//class in test
	Category cat;
	
	@Before
	public void setUp() throws Exception {
		cat = new Category();
	}

	@After
	public void tearDown() throws Exception {
		cat = null;
	}

	@Test
	public void haveColor_test() {
		assertNotNull(cat.getColor());
	}
	
	@Test
	public void haveName_test(){
		assertNotNull(cat.getName());
	}
}
