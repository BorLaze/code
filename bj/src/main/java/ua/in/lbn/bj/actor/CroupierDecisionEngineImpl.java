package ua.in.lbn.bj.actor;

import ua.in.lbn.bj.game.Game;

public class CroupierDecisionEngineImpl implements GamblerDecisionEngine {

    private final Gambler gambler;

    public CroupierDecisionEngineImpl(Gambler gambler) {
        this.gambler = gambler;
    }

    @Override
    public Decision decision() {
        return gambler.getHand().getScore() < Game.CROUPIER_HIT_SCORE
                ? Decision.HIT
                : Decision.STAY;

    }
}
