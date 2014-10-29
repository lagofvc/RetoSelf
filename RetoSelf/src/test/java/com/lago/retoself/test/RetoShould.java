package com.lago.retoself.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lago.retoself.domain.Reto;

public class RetoShould {

	//class in test
	Reto reto;
	
	@Before
	public void setUp() throws Exception {
		reto = new Reto();
	}
	
	@After
	public void tearDown() throws Exception {
		reto = null;
	}
	
	@Test
	public void haveType_test(){
		assertNotNull(reto.getType());
	}
}
