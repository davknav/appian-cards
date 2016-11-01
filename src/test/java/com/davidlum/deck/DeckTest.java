package com.davidlum.deck;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Unit tests for the Deck class.
 */
public class DeckTest
{
    @Test
    public void testNewDeckIsSorted() {
        Deck deck = new Deck();

        // In a sorted deck, the first card is the two of clubs.
        Card twoClubs = deck.dealOneCard();
        assertSame( "First card is a Two", twoClubs.getNumber(), 2);
        assertSame( "First card is a CLUBS", twoClubs.getSuit(), Suit.CLUBS);

        deck.burn( 50);  // Discard all but the last.

        // In a sorted deck, the last card is the Ace of spades.
        Card aceSpades = deck.dealOneCard();
        assertSame( "Last card is an Ace", aceSpades.getNumber(), 14);
        assertSame( "Last card is a SPADES", aceSpades.getSuit(), Suit.SPADES);
    }

    @Test( expected = IllegalStateException.class)
    public void testEmptyDeckNeg() {
        Deck deck = new Deck();
        assertFalse( "Not empty initially", deck.isEmpty());
        deck.burn( 52); // Discard all.
        assertTrue( "Empty after 52 deals", deck.isEmpty());
        try {
            deck.dealOneCard();
        } catch ( IllegalStateException e) {
            assertEquals( "The deck has no more cards in it.", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testShuffleBeginning() {
        // Shuffle until we get a non-two-of-clubs as the first card.  Give up after 100 tries.
        Card twoClubs = new Deck().dealOneCard();
        int i = 0;
        for ( ; i<100; i++) {
            Deck deck = new Deck();
            deck.shuffle();
            if ( ! twoClubs.equals( deck.dealOneCard())) {
                break; // Good.
            }
        }
        assertTrue( "Shuffling yielded a non-2-of-clubs as the first card", i < 100);
    }

    @Test
    public void testShuffleEnd() {
        // Shuffle until we get a non-ace as the last card.  Give up after 100 tries.
        int i = 0;
        for ( ; i<100; i++) {
            Deck deck = new Deck();
            deck.shuffle();
            deck.burn( 51);
            if ( deck.dealOneCard().getNumber() != 14) {
                break; // Good.
            }
        }
        assertTrue( "Shuffling yielded a non-ace as the last card", i < 100);
    }

    @Test
    public void testShuffleSameSize() {
        // Show that shuffling yields a same-sized deck as expected.
        Deck deck = new Deck();
        deck.shuffle();
        deck.burn( 52);
        assertTrue( "The deck only had 52 cards", deck.isEmpty());
    }

    @Test
    public void testShuffleEmpty() {
        // Show that shuffling an empty deck is OK.
        Deck deck = new Deck();
        deck.burn( 52);
        deck.shuffle();
        assertTrue( "The deck is still empty", deck.isEmpty());
    }

    @Test
    public void testShuffleSingleton() {
        // Show that shuffling a deck with one card is OK.
        Deck deck = new Deck();
        deck.burn( 51);
        deck.shuffle();
        assertEquals( "One-card shuffle was OK", new Card( 14, Suit.SPADES), deck.dealOneCard());
        assertTrue( "The deck is eventually empty", deck.isEmpty());
    }

    @Test
    public void testShuffleHalfDeck() {
        // Show that shuffling in the middle of a deck yields the same size deck as before.
        Deck deck = new Deck();
        deck.burn( 26);
        deck.shuffle();
        assertFalse( "Not empty yet", deck.isEmpty());
        deck.burn( 26);
        assertTrue( "Is empty now", deck.isEmpty());
    }

    @Test
    public void testShuffleYieldsSame() {
        // Show that shuffling doesn't change the cards.
        Deck cleanDeck = new Deck();
        List<Card> cleanList = IntStream.rangeClosed( 1, 52)
                .mapToObj( i -> cleanDeck.dealOneCard())
                .collect( Collectors.toList());
        assertSame( "The clean deck had 52 cards", 52, cleanList.size());
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();
        List<Card> shuffledList = IntStream.rangeClosed( 1, 52)
                .mapToObj( i -> shuffledDeck.dealOneCard())
                .collect( Collectors.toList());
        assertSame( "The shuffled deck had 52 cards", 52, shuffledList.size());
        // Sort both and compare.
        Collections.sort( cleanList);
        Collections.sort( shuffledList);
        assertEquals( "The shuffled deck has the same cards as the clean one",
                shuffledList, cleanList);
    }
}
