package ua.in.lbn.bj.table;

public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    public enum Suit {
        SPADES("♠"),
        CLOVERS("♣"),
        DIAMONDS("♦"),
        HEARTS("♥");

        private final String shape;

        Suit(String shape) {
            this.shape = shape;
        }

        @Override
        public String toString() {
            return shape;
        }
    }

    public enum Rank {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        @Override
        public String toString() {
            if (value == JACK.value) {
                return "J";
            }
            if (value == QUEEN.value) {
                return "Q";
            }
            if (value == KING.value) {
                return "K";
            }
            if (value == ACE.value) {
                return "A";
            }
            return String.valueOf(value);
        }
    }
}
