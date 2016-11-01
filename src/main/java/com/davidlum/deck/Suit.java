package com.davidlum.deck;

/**
 * Represents the suit of a playing card.  Like all Java enums, the {@link Suit} class is
 * {@link Comparable}, with a lower suit less than a higher suit.  As in standard poker, an
 * alphabetically earlier suit is considered lower than an alphabetically later suit, e.g. a
 * {@link #CLUBS club} is lower than a {@link #SPADES spade}.
 */
public enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES,
}
