//public class Sen {
//    public class PlayingCardGame implements Runnable{
//    /*
//    o Skapa en blandad kortlek som spelet ska använda sig av
//    o Kunna spela som en eller flera spelare (om spelet man valt att implementera spelas av flera spelare)
//    o Kunna vinna / förlora / få oavgjort i spelet
//    o Innehålla en meny som ger användaren följande alternativ:
//    ▪ Spela ett parti
//    ▪ Visa spelregler
//    ▪ Avsluta spelet
//
//    Lägga till grafik, ljudeffekter???
//     */
//
//    long xTime = System.nanoTime();
//    public static boolean keepPlaying = true;
//    public int Hz = 100; //hur ofta skärmen ska uppdateras
//
//        GUI gui = new GUI();
//
//        public static void main(String[] args) {
//            new Thread(new PlayingCardGame()).start();
//        }
//
//        @Override
//        public void run() {
//        while(keepPlaying){
//            if(System.nanoTime() - xTime >= 1000000000/Hz){
//                gui.refresher();
//                gui.repaint();
//                xTime = System.nanoTime();
//            }
//        }
//        }
//    }
//
//import javax.swing.*;
//import java.util.*;
//import java.awt.*;
//
//public class GUI extends JFrame {
//
//    //hanterar vilken grafik som ska ritas upp
//    private State state = State.MENU;
//
//    //storleken på spelfönstret
//    int width = 1400;
//    int height = 750;
//
//    //färger som används i spelet
//    Color backgroundColor = new Color(39, 119, 20);
//    Color buttonColor = new Color(255, 255, 255);
//
//    //knappar
//    Button startGameButton = new Button();
//    Button showRulesButton = new Button();
//    Button endGameButton = new Button();
//
//    Button undoButton = new Button();
//    Button toMenuButton = new Button();
//
//    //sätter textstil
//    Font menuHeadlineFont = new Font("arial", Font.BOLD, 50);
//    Font buttonFont = new Font("arial", Font.BOLD, 30);
//    Font normalTextFont = new Font("arial", Font.PLAIN, 20);
//
//    //Grafiken för spelet - skapar spelfönstret
//    public GUI() {
//        this.setSize(width + 6, height + 29); //6 och 29 läggs till för att vi inte ska ha någon spelgrafik på fönstrets kanter
//        this.setTitle("Johanna's card game - Pyramid Solitaire");
//        this.setVisible(true);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(null);
//        Board board = new Board();
//        this.setContentPane(board);
//
//    }
//
//    public class Board extends JPanel {
//
//        public void paintComponents(Graphics g) {
//            g.setColor(backgroundColor);
//            g.fillRect(0, 0, width, height);
//
//            //här ska det som hör till menyn finnas (knappar etc)
//            if (state == State.MENU) {
//
//                //ritar upp rubriken i menyn
//                g.setFont(menuHeadlineFont);
//                g.setColor(Color.white);
//                g.drawString("PYRAMID SOLITAIRE", 440, 80);
//
//                //ritar upp meny-knapparna
//                startGameButton.setBounds(440, 200, 200, 50);
//                this.add(startGameButton);
//                showRulesButton.setBounds(440, 300, 200, 50);
//                this.add(showRulesButton);
//                //måste man rensa allt gammalt om någon väljer att gå till menyn?
//            }
//
//            //här ska det som hör till spelet finnas (kortuppritning, knappar etc)
//            else if (state == State.GAME) {
//
//            }
//
//            //här ska grafiken som visas om man vinner finnas + highscore?
//            else if (state == State.WIN) {
//
//            }
//
//        }
//
//    }
//}
//
//
//
//}
