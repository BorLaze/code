package ua.in.lbn.bj.table;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newLinkedList;

public class Deck52 {

    private List<Card> cards = newLinkedList();

    public Deck52() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public Optional<Card> next() {
        if (cards.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(cards.remove(0));
    }

}
