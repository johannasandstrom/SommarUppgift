public class PlayingCardGame implements Runnable {

    private GUI gui;
    private static PanelContainer panelContainer = new PanelContainer();

    public PlayingCardGame(){

    }

    public static void main(String[] args) {
        new Thread(new PlayingCardGame()).start();
    }

    @Override
    public void run() {
        gui = new GUI(panelContainer);

    }
}
