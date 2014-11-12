package com.lago.retoself.test;

import com.lago.retoself.domain.Challenge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ChallengeShould {

    //class in test
    Challenge challenge;

    @Before
    public void setUp() throws Exception {
        challenge = new Challenge();
    }

    @After
    public void tearDown() throws Exception {
        challenge = null;
    }

    @Test
    public void haveType_test(){
        assertNotNull(challenge.getType());
    }

    @Test
    public void haveName_test(){
        assertNotNull(challenge.getName());
    }

    @Test
    public void notYetBeMet_test(){
        assertFalse(challenge.getHasBeenMet());
    }
}
