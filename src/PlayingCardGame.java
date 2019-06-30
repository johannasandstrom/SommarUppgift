public class PlayingCardGame implements Runnable{
    /*
    o Skapa en blandad kortlek som spelet ska använda sig av
    o Kunna spela som en eller flera spelare (om spelet man valt att implementera spelas av flera spelare)
    o Kunna vinna / förlora / få oavgjort i spelet
    o Innehålla en meny som ger användaren följande alternativ:
    ▪ Spela ett parti
    ▪ Visa spelregler
    ▪ Avsluta spelet

    Lägga till grafik, ljudeffekter???
     */

    GUI gui = new GUI();

    public static void main(String[] args) {
        new Thread(new PlayingCardGame()).start();
    }

    @Override
    public void run() {

    }
}
