package ua.in.lbn.bj.game;

import ua.in.lbn.bj.actor.Croupier;
import ua.in.lbn.bj.actor.Player;
import ua.in.lbn.bj.table.Card;
import ua.in.lbn.bj.table.Deck52;

public class Game {

    public static final int WIN_SCORE = 21;
    public static final int CROUPIER_HIT_SCORE = 17;

    private Deck52 deck52;

    private Croupier croupier;
    private Player player;

    public Game() {
        deck52 = new Deck52();

        croupier = new Croupier(this);
        player = new Player(this);
    }

    public Croupier getCroupier() {
        return croupier;
    }

    public Player getPlayer() {
        return player;
    }

    public Card nextCard() throws BlackJackException {
        return deck52.next().orElseThrow(() -> new BlackJackException("no more cards in deck"));
    }

}
