package cardDeckClasses;

public class PlayingCard {
    private Rank rank;
    private Suit suit;
    private boolean isHidden;
    private Position pos;

    public PlayingCard(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
        isHidden = true;
        pos = new Position();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    @Override
    public String toString(){
        switch(suit){
            case CLUBS:
                return rank.getDescription() + " of clubs";
            case HEARTS:
                return rank.getDescription() + " of hearts";
            case SPADES:
                return rank.getDescription() + " of spades";
            default:
                return rank.getDescription() + " of diamonds";
        }
    }
}
