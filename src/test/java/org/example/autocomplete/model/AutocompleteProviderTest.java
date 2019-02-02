package org.example.autocomplete.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class AutocompleteProviderTest {
    private static final String PASSAGE = "The third thing () that I need to tell you, is that 'this thing does not " +
            "think thoroughly.'";
    private AutocompleteProvider provider;

    @Before
    public void setUp() {
        provider = new AutocompleteProvider();
    }

    /**
     * Tests that the correct candidates are provided in the correct order after training.
     */
    @Test
    public void getWordsTest() {
        provider.train(PASSAGE);
        List<Candidate> candidates = provider.getWords("thi");

        assertNotNull(candidates);
        assertEquals(4, candidates.size());
        checkOrder(candidates);

        for (Candidate candidate: candidates) {
            String word = candidate.getWord();

            if (!word.startsWith("thi")) {
                fail("incorrect candidate returned: " + word);
            }
        }

        Candidate candidate = candidates.get(0);
        assertEquals("thing", candidate.getWord());
        assertEquals(Integer.valueOf(2), candidate.getConfidence());
    }

    /**
     * Tests that all candidates are provided in the correct order after training when given an empty fragment.
     */
    @Test
    public void getWordsEmptyFragmentTest() {
        provider.train(PASSAGE);
        List<Candidate> candidates = provider.getWords("");

        assertNotNull(candidates);
        assertEquals(15, candidates.size());
        checkOrder(candidates);
    }

    /**
     * Tests that an empty list is returned when the provider has not been trained.
     */
    @Test
    public void getWordsNoTrainingTest() {
        List<Candidate> candidates = provider.getWords("thi");

        assertNotNull(candidates);
        assertEquals(0, candidates.size());
        checkOrder(candidates);
    }

    /**
     * Tests that an empty list is returned when an unseen fragment is provided.
     */
    @Test
    public void getWordsNoSuggestionsTest() {
        provider.train(PASSAGE);
        List<Candidate> candidates = provider.getWords("apple");

        assertNotNull(candidates);
        assertEquals(0, candidates.size());
        checkOrder(candidates);
    }

    /**
     * Helper to ensure that candidates are sorted in descending order of confidence.
     */
    private void checkOrder(List<Candidate> candidates) {
        if (candidates.size() > 0) {
            Integer previousConfidence = candidates.get(0).getConfidence();

            for (Candidate candidate: candidates) {
                if (candidate.getConfidence() > previousConfidence) {
                    fail("candidate order is incorrect");
                }

                previousConfidence = candidate.getConfidence();
            }
        }
    }
}
