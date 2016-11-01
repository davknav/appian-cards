package com.davidlum.deck;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A standard deck of 52 {@link Card cards}.
 */
public class Deck {
    /**
     * the cards in 'this' deck.  We're using an ArrayList rather than an array because it
     * makes the shuffle code simpler and less error prone, and we assume that the performance
     * difference is negligible.
     */
    private List<Card> m_Cards;

    /**
     * Creates a new deck with the cards in sorted order from 2-of-clubs, 2-of-diamonds, ...
     * Ace-of-spades.
     */
    public Deck() {
        m_Cards = new ArrayList<>();
        for ( int number = 2; number<=14; number++) {
            for ( Suit suit : Suit.values()) {
                m_Cards.add( new Card( number, suit));
            }
        }
    }

    /**
     * Returns the next card in 'this' deck.
     *
     * @return the next card, which is never 'null'
     * @throws IllegalStateException if 'this' deck has no cards remaining
     */
    public Card dealOneCard() {
        if ( this.isEmpty()) {
            throw new IllegalStateException( Msg.get( "Deck_emptyDeck"));
        }
        return m_Cards.remove( 0);
    }

    /**
     * Tests to see if 'this' deck is empty.
     * @return 'true' iff all the cards in 'this' deck have already been dealt
     */
    public boolean isEmpty() {
        return m_Cards.isEmpty();
    }

    /**
     * Randomizes the cards in 'this' deck.  It's legal to call shuffle on any Deck, including
     * a full deck, a half-dealt deck, and even a completely empty deck.
     */
    public void shuffle() {
        // Use of the {@link Collections#shuffle} method is outlawed :(.
        // So we'll dig into Wikipedia and implement a decent shuffle ourselves using the
        // Fisher-Yates algorithm.
        Card[] toShuffle = m_Cards.toArray( new Card[m_Cards.size()]);
        int n = toShuffle.length;
        Random rand = new Random();
        for ( int i=(n-1); i>0; i--) {
            int j = rand.nextInt( i+1);
            Card tmp = toShuffle[i];
            toShuffle[i] = toShuffle[j];
            toShuffle[j] = tmp;
        }
        m_Cards.clear();
        m_Cards.addAll( Arrays.asList( toShuffle));
    }

    @Override
    public String toString() {
        return "Deck: " + m_Cards.stream()
                .map( card -> card.toString())
                .collect( Collectors.joining( ", "));
    }

    /**
     * Deals 'numToBurn' cards from 'this' deck and discards them.
     * @param numToBurn the number of cards to discard
     * @throws IllegalStateException if the deck contains less than 'numToBurn' cards
     */
    public void burn( int numToBurn) {
        for ( int i=0; i<numToBurn; i++) {
            this.dealOneCard();
        }
    }
}
