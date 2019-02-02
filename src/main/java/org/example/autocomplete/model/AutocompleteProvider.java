package org.example.autocomplete.model;

import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.*;

/**
 * Provider for suggested words based on training data.
 */
public class AutocompleteProvider {
    private PatriciaTrie<Candidate> candidates; // For fuzzy partial candidate lookup
    private HashMap<String, Candidate> candidateMap; // For exact match candidate lookup


    /**
     * Initializes an empty AutocompleteProvider.
     */
    public AutocompleteProvider() {
        this.candidates = new PatriciaTrie<>();
        this.candidateMap = new HashMap<>();
    }

    /**
     * Retrieves a list of candidates for which the supplied fragment is a prefix, sorted in decreasing order of
     * confidence. Case is ignored.
     *
     * @param fragment
     * @return list of candidates ordered by confidence
     */
    public List<Candidate> getWords(String fragment) {
        ArrayList<Candidate> candidates = new ArrayList<>(this.candidates.prefixMap(fragment.toLowerCase()).values());
        Collections.sort(candidates);

        return candidates;
    }


    /**
     * Trains the algorithm with the provided passage. Leading and trailing spaces, punctuations, and special characters
     * are ignored. Case is also ignored.
     *
     * @param passage training passage
     */
    public void train(String passage) {
        String[] terms = passage.toLowerCase().split(" ");

        for (String term: terms) {
            //Trim leading and trailing punctuation/special characters
            String formatted = term.replaceAll("[^a-z0-9]+$", "")
                    .replaceAll("^[^a-z0-9]+", "");
            Candidate candidate = candidateMap.get(formatted);

            if (candidate == null) {
                candidate = new Candidate(formatted);

                candidates.put(formatted, candidate);
                candidateMap.put(formatted, candidate);
            } else {
                candidate.increment();
            }
        }
    }
}
