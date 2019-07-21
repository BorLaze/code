package ua.in.lbn.bj.actor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.in.lbn.bj.game.BlackJackException;
import ua.in.lbn.bj.game.Game;
import ua.in.lbn.bj.table.Card;

import static com.google.common.collect.Lists.newLinkedList;

public abstract class Gambler {

    private Hand hand;
    private Game game;

    Gambler(Game game) {
        this.hand = new Hand();
        this.game = game;
    }

    public Status hit() throws BlackJackException {
        Card card = game.nextCard();
        hand.hit(card);

        return getStatus();
    }

    Hand getHand() {
        return hand;
    }

    Status getStatus() {
        int score = hand.getScore();

        if (score > Game.WIN_SCORE) {
            return Status.BUSTED;
        }

        List<Card> cards = hand.getCards();
        if (score == Game.WIN_SCORE &&
                (cards.get(0).getRank() == Card.Rank.ACE && cards.get(1).getRank().getValue() == 10 ||
                        cards.get(1).getRank() == Card.Rank.ACE && cards.get(0).getRank().getValue() == 10)
        ) {
            return Status.BLACKJACK;
        }

        return Status.OK;
    }

    class Hand {

        private List<Card> cards = newLinkedList();

        void hit(Card card) {
            cards.add(card);
        }

        int getScore() {
            Map<Boolean, List<Card>> collect = cards.stream()
                    .collect(
                            Collectors.partitioningBy(c -> c.getRank() == Card.Rank.ACE)
                    );
            int scoreWithoutAces = collect.get(false).stream()
                    .mapToInt(c -> c.getRank().getValue())
                    .sum();

            List<Card> acesList = collect.get(true);

            if (acesList.isEmpty()) {
                return scoreWithoutAces;
            }

            int scoreWithAces = scoreWithoutAces + acesList.size(); // treat all ACEs as 1
            return scoreWithAces + 10 <= Game.WIN_SCORE
                    ? scoreWithoutAces + acesList.size() + 10       // treat first ACE as 11
                    : scoreWithoutAces + acesList.size();
        }

        List<Card> getCards() {
            return Collections.unmodifiableList(cards);
        }
    }

    public enum Status {
        BLACKJACK,
        BUSTED,
        OK
    }
}
