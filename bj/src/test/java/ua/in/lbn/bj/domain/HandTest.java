package ua.in.lbn.bj.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void value12() {
        Hand h = new Hand();
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(12, h.getValue());
    }

    @Test
    void value20() {
        Hand h = new Hand();
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(20, h.getValue());
    }

    @Test
    void value22() {
        Hand h = new Hand();
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(22, h.getValue());
    }

    @Test
    void value21() {
        Hand h = new Hand();
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(21, h.getValue());
    }

    @Test
    void value14() {
        Hand h = new Hand();
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.THREE));
        h.take(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(14, h.getValue());
    }
}