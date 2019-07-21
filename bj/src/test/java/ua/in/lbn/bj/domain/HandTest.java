package ua.in.lbn.bj.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void value12() {
        Hand h = new Hand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(12, h.getScore());
        assertEquals(GameCtx.Status.OK, h.getStatus());
    }

    @Test
    void value20() {
        Hand h = new Hand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(20, h.getScore());
        assertEquals(GameCtx.Status.OK, h.getStatus());
    }

    @Test
    void value22() {
        Hand h = new Hand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(22, h.getScore());
        assertEquals(GameCtx.Status.BUSTED, h.getStatus());
    }

    @Test
    void value21() {
        Hand h = new Hand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(21, h.getScore());
        assertEquals(GameCtx.Status.OK, h.getStatus());
    }

    @Test
    void value14() {
        Hand h = new Hand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.THREE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(14, h.getScore());
        assertEquals(GameCtx.Status.OK, h.getStatus());
    }

    @Test
    void valueBJ() {
        Hand h = new Hand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.QUEEN));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(21, h.getScore());
        assertEquals(GameCtx.Status.BLACKJACK, h.getStatus());
    }
}