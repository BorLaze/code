package ua.in.lbn.bj.table;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Deck52Test {

    @Test
    void takeAllCards() {
        Deck52 d = new Deck52();

        Optional<Card> optionalCard = Optional.empty();
        for (int i = 0; i < 52; i++) {
            optionalCard = d.next();
        }

        assertTrue(optionalCard.isPresent());
        optionalCard = d.next();
        assertFalse(optionalCard.isPresent());
    }

    @Test
    void shuffle() {
        Deck52 d = new Deck52();

        Card c1 = d.next().orElseThrow(() -> new RuntimeException("no card 1"));
        Card c2 = d.next().orElseThrow(() -> new RuntimeException("no card 2"));
        Card c3 = d.next().orElseThrow(() -> new RuntimeException("no card 3"));

        assertFalse(
                c1.getRank() == Card.Rank.ACE &&
                        c2.getRank() == Card.Rank.TWO &&
                        c3.getRank() == Card.Rank.THREE
        );
    }
}