package ua.in.lbn.bj.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newLinkedList;

public class Hand {

    private List<Card> cards = newLinkedList();

    public void hit(Card card) {
        cards.add(card);
    }

    public GameCtx.Status getStatus() {
        int score = getScore();

        if (score > GameCtx.WIN_SCORE) {
            return GameCtx.Status.BUSTED;
        }

        if (score == GameCtx.WIN_SCORE &&
                (cards.get(0).getRank() == Card.Rank.ACE && cards.get(1).getRank().getValue() == 10 ||
                        cards.get(1).getRank() == Card.Rank.ACE && cards.get(0).getRank().getValue() == 10)
        ) {
            return GameCtx.Status.BLACKJACK;
        }

        return GameCtx.Status.OK;
    }

    public int getScore() {
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

        int scoreWithAcesAs1 = scoreWithoutAces + acesList.size();
        return scoreWithAcesAs1 + 10 <= GameCtx.WIN_SCORE
                ? scoreWithoutAces + acesList.size() + 10   // treat first ACE as 11
                : scoreWithoutAces + acesList.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
