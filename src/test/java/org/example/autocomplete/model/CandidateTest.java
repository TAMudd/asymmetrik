package org.example.autocomplete.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CandidateTest {

    /**
     * Tests confidence incrementation.
     */
    @Test
    public void incrementTest() {
        Candidate alpha = new Candidate("alpha");
        assertEquals(Integer.valueOf(1), alpha.getConfidence());
        alpha.increment();
        assertEquals(Integer.valueOf(2), alpha.getConfidence());
    }

    /**
     * Tests that two different words with equal confidences are considered equal.
     */
    @Test
    public void compareToEqualTest() {
        Candidate alpha = new Candidate("alpha");
        Candidate bravo = new Candidate("bravo");

        assertEquals(0, alpha.compareTo(bravo));
    }

    /**
     * Tests that two words with different confidences are considered unequal.
     * Larger confidences are "less than" smaller ones for ordering.
     */
    @Test
    public void compareToUnequalTest() {
        Candidate alpha = new Candidate("alpha");
        Candidate alphaToo = new Candidate("alpha");
        alpha.increment();

        assertTrue(alpha.compareTo(alphaToo) < 0);
    }
}
