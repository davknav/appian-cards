package com.davidlum.deck;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the Card class.
 */
public class CardTest
{
    @Test
    public void testCardsEqual() {
        Card twoClub = new Card( 2, Suit.CLUBS);
        Card anotherTwoClub = new Card( 2, Suit.CLUBS);
        assertEquals( "Two 2-of-clubs are equal", twoClub, anotherTwoClub);
    }

    @Test
    public void testCardsNotEqual() {
        Card twoClub = new Card( 2, Suit.CLUBS);
        Card threeClub = new Card( 3, Suit.CLUBS);
        assertFalse( "Two cards with different numbers are not equal", twoClub.equals( threeClub));

        Card twoHeart = new Card( 2, Suit.HEARTS);
        assertFalse( "Two cards with different suits are not equal", twoClub.equals( twoHeart));
    }

    @Test
    public void testCardsCompareTo() {
        Card tenSpade = new Card( 10, Suit.SPADES);
        Card jackSpade = new Card( 11, Suit.SPADES);
        assertTrue( "10 < Jack", tenSpade.compareTo( jackSpade) < 0);

        Card jackClubs = new Card( 11, Suit.CLUBS);
        Card jackDiamonds = new Card( 11, Suit.DIAMONDS);
        Card jackHearts = new Card( 11, Suit.HEARTS);
        Card jackSpades = new Card( 11, Suit.SPADES);
        assertTrue( "CLUBS < DIAMONDS", jackClubs.compareTo( jackDiamonds) < 0);
        assertTrue( "DIAMONDS < HEARTS", jackDiamonds.compareTo( jackHearts) < 0);
        assertTrue( "HEARTS < SPADES", jackHearts.compareTo( jackSpades) < 0);
    }

    @Test( expected = IllegalArgumentException.class)
    public void testBadHighCardNumberNeg() {
        try {
            Card c = new Card( 15, Suit.CLUBS);
        } catch ( IllegalArgumentException e) {
            assertEquals( "The number \"15\" is not a valid card number.", e.getMessage());
            throw e;
        }
    }

    @Test( expected = IllegalArgumentException.class)
    public void testBadLowCardNumberNeg() {
        try {
            Card c = new Card( -1, Suit.CLUBS);
        } catch ( IllegalArgumentException e) {
            assertEquals( "The number \"-1\" is not a valid card number.", e.getMessage());
            throw e;
        }
    }

    @Test( expected = IllegalArgumentException.class)
    public void testBadNullSuitNeg() {
        try {
            Card c = new Card( 12, null);
        } catch ( IllegalArgumentException e) {
            assertEquals( "Cannot construct a Card with a null Suit.", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCardNames() {
        assertEquals( "Two of CLUBS", new Card( 2, Suit.CLUBS).toString());
        assertEquals( "Three of DIAMONDS", new Card( 3, Suit.DIAMONDS).toString());
        assertEquals( "Four of HEARTS", new Card( 4, Suit.HEARTS).toString());
        assertEquals( "Five of SPADES", new Card( 5, Suit.SPADES).toString());
        assertEquals( "Six of CLUBS", new Card( 6, Suit.CLUBS).toString());
        assertEquals( "Seven of CLUBS", new Card( 7, Suit.CLUBS).toString());
        assertEquals( "Eight of CLUBS", new Card( 8, Suit.CLUBS).toString());
        assertEquals( "Nine of CLUBS", new Card( 9, Suit.CLUBS).toString());
        assertEquals( "Ten of CLUBS", new Card( 10, Suit.CLUBS).toString());
        assertEquals( "Jack of CLUBS", new Card( 11, Suit.CLUBS).toString());
        assertEquals( "Queen of CLUBS", new Card( 12, Suit.CLUBS).toString());
        assertEquals( "King of CLUBS", new Card( 13, Suit.CLUBS).toString());
        assertEquals( "Ace of CLUBS", new Card( 14, Suit.CLUBS).toString());
    }
}
