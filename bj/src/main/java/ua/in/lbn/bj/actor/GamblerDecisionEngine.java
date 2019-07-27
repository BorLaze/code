package ua.in.lbn.bj.actor;

public interface GamblerDecisionEngine {

    Decision decision();

    enum Decision {
        HIT,
        STAY
    }
}
