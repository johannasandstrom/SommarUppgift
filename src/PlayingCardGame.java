public class PlayingCardGame implements Runnable{

    GUI gui = new GUI();

    public static void main(String[] args) {
        new Thread(new PlayingCardGame()).start();
    }

    @Override
    public void run() {

    }
}
