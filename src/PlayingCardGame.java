import cardDeckClasses.PlayingCardDeck;

public class PlayingCardGame implements Runnable{

    private GUI gui;
    private static Board board = new Board();

    public PlayingCardGame(){
    }

    public static void main(String[] args) {
        PlayingCardDeck deck = new PlayingCardDeck();
        deck.shuffleCardDeck();
        new Thread(new PlayingCardGame()).start();
    }

    @Override
    public void run() {
        gui = new GUI(board);
    }

}
