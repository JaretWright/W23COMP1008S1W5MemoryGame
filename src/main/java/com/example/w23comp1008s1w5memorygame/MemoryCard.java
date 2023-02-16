package com.example.w23comp1008s1w5memorygame;

public class MemoryCard extends Card {
    private boolean matched;

    /**
     * This is a constructor.  It is called when we want to instantiate (create an
     * instance) of our object.
     * It looks like a method, but does not have a return type and must have the
     * exact same name as the class
     *
     * @param faceNumber
     * @param suit
     */
    public MemoryCard(String faceNumber, String suit) {
        super(faceNumber, suit);
        matched = false;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isSameCard(MemoryCard otherCard)
    {
        return (this.getSuit().equals(otherCard.getSuit())
               && this.getFaceName().equals(otherCard.getFaceName()));
    }

    public String toString()
    {
        return super.toString() + " matched: " + matched;
    }
}
