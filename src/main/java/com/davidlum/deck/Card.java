package com.davidlum.deck;

import java.util.Objects;

/**
 * A standard playing card with a suit and a number.
 */
public class Card implements Comparable<Card> {
    /**
     * the human-readable card names indexed by a card's {@link #getNumber() number}.
     * The first two entries in this array are padding and do not correspond to any actual card
     * since no card has the number 0 or 1.
     */
    private static final String[] CARD_NAMES = new String[] {
            "PADDING",
            "PADDING",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Jack",
            "Queen",
            "King",
            "Ace",
    };

    private final int m_Number;
    private final Suit m_Suit;

    /**
     * Constructs a new card.
     * @param number the number of the card.  Must be one of the valid values returned by
     *               {@link #getNumber()}.
     * @param suit the suit of the card.  Must not be null.
     * @throws IllegalArgumentException if 'number' is invalid or 'suit' is null
     */
    public Card( int number, Suit suit) {
        if ( number < 2 || number >= CARD_NAMES.length) {
            throw new IllegalArgumentException( Msg.get( "Card_illegalNumber", number));
        }
        if ( suit == null) {
            throw new IllegalArgumentException( Msg.get( "Card_nullSuit"));
        }
        m_Number = number;
        m_Suit = suit;
    }

    /**
     * Returns the number of 'this' card, which will always be one of the following:
     * <ul>
     *   <li>2 = Two</li>
     *   <li>3 = Three</li>
     *   <li>4 = Four</li>
     *   <li>5 = Five</li>
     *   <li>6 = Six</li>
     *   <li>7 = Seven</li>
     *   <li>8 = Eight</li>
     *   <li>9 = Nine</li>
     *   <li>10 = Ten</li>
     *   <li>11 = Jack</li>
     *   <li>12 = Queen</li>
     *   <li>13 = King</li>
     *   <li>14 = Ace</li>
     * </ul>
     *
     * @return 'this' card's number
     */
    public int getNumber() {
        return m_Number;
    }

    /**
     * Returns the suit of 'this' card.
     *
     * @return 'this' card's suit
     */
    public Suit getSuit() {
        return m_Suit;
    }

    /**
     * Returns a human-readable representation of 'this' card.
     * @return a card description such as "6 of CLUBS"
     */
    @Override
    public String toString() {
        return String.format( "%1s of %2s", CARD_NAMES[m_Number], m_Suit);
    }

    /**
     * Compares 'this' card with 'o'.  If 'this' card is less than 'o' it means that 'o' would
     * beat this one in a poker game where the house rules take the suit into account to break
     * ties between two same-number cards.
     *
     * @param o the card against which 'this' is being compared
     * @return see {@link Comparable#compareTo(Object)}
     */
    public int compareTo( Card o) {
        if ( m_Number < o.m_Number) {
            return -1;
        } else if ( m_Number > o.m_Number) {
            return 1;
        } else {
            return m_Suit.compareTo( o.m_Suit);
        }
    }

    /**
     * Overridden so that the natural ordering of this class is consistent with equals.
     */
    @Override
    public boolean equals( Object o) {
        return (o instanceof Card) && this.compareTo( (Card) o) == 0;
    }

    /**
     * Overridden so that {@link #equals(Object) equal} objects have the same hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash( m_Number, m_Suit);
    }
}
