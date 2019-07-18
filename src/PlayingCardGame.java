import cardDeckClasses.PlayingCardDeck;

public class PlayingCardGame implements Runnable{

    private GUI gui;
    private static Board board = new Board();

    public PlayingCardGame(){
    }

    public static void main(String[] args) {
        new Thread(new PlayingCardGame()).start();
//        while(true){
//            if(board.getState() == State.GAME){
//                PlayingCardDeck deck = new PlayingCardDeck();
//                deck.shuffleCardDeck();
//            }
//        }
    }

    @Override
    public void run() {
        gui = new GUI(board);
    }

}
