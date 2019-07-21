package ua.in.lbn.bj.actor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.in.lbn.bj.game.Game;
import ua.in.lbn.bj.table.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GamblerTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        game = new Game();
    }

    @Test
    void value20() {
        Gambler g = game.getPlayer();
        Gambler.Hand h = g.getHand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(20, h.getScore());
        assertEquals(Gambler.Status.OK, g.getStatus());
    }

    @Test
    void value12() {
        Gambler g = game.getPlayer();
        Gambler.Hand h = g.getHand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(12, h.getScore());
        assertEquals(Gambler.Status.OK, g.getStatus());
    }

    @Test
    void value22() {
        Gambler g = game.getPlayer();
        Gambler.Hand h = g.getHand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(22, h.getScore());
        assertEquals(Gambler.Status.BUSTED, g.getStatus());
    }

    @Test
    void value21() {
        Gambler g = game.getPlayer();
        Gambler.Hand h = g.getHand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.NINE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(21, h.getScore());
        assertEquals(Gambler.Status.OK, g.getStatus());
    }

    @Test
    void value14() {
        Gambler g = game.getPlayer();
        Gambler.Hand h = g.getHand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.THREE));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(14, h.getScore());
        assertEquals(Gambler.Status.OK, g.getStatus());
    }

    @Test
    void valueBJ() {
        Gambler g = game.getPlayer();
        Gambler.Hand h = g.getHand();
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.QUEEN));
        h.hit(new Card(Card.Suit.CLOVERS, Card.Rank.ACE));

        assertEquals(21, h.getScore());
        assertEquals(Gambler.Status.BLACKJACK, g.getStatus());
    }
}