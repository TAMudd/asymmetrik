package org.example.autocomplete.model;

/**
 * Class for potential autocomplete candidates.
 */
public class Candidate implements Comparable<Candidate> {
    private String word;
    private Integer confidence;

    /**
     * Initializes a new autocomplete Candidate. Confidence is initialized to 1.
     * @param word a word
     */
    public Candidate(String word) {
        this.word = word;
        this.confidence = 1;
    }

    /**
     * Return the autocomplete candidate word.
     *
     * @return the autocomplete candidate word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the confidence of this Candidate.
     *
     * @return returns the confidence for the Candidate.
     */
    public Integer getConfidence() {
        return confidence;
    }

    /**
     * Increments the confidence of this Candidate.
     */
    public void increment() {
        this.confidence++;
    }

    /**
     * Comparison is done based on confidence. Values reversed so confidences are in descending order. Candidates with
     * equal confidences are considered equal.
     *
     * @param o the other candidate to be compared.
     * @return a positive integer, zero, or a negative integer as this object is less than, equal to, or greater than
     * the specified object respectively.
     */
    public int compareTo(Candidate o) {
        return -this.confidence.compareTo(o.confidence);
    }

    @Override
    public String toString() {
        return "\"" + word + "\" (" + confidence + ")";
    }
}
