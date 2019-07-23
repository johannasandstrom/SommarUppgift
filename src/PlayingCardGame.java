public class PlayingCardGame implements Runnable {

    private GUI gui;
    private static PanelContainer panelContainer = new PanelContainer();

    private PlayingCardGame(){

    }

    //När vi kör vår main-metod startas en tråd upp som i sin tur startar upp en GUI (graphics user interface).
    public static void main(String[] args) {
        new Thread(new PlayingCardGame()).start();
    }

    @Override
    public void run() {
        gui = new GUI(panelContainer);

    }
}
