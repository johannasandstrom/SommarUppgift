import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    //hanterar vilken grafik som ska ritas upp
    private State state = State.MENU;

    //storleken på spelfönstret
    int width = 1400;
    int height = 750;

    //färger som används i spelet
    Color backgroundColor = new Color(39, 119, 20);

    //Grafiken för spelet - skapar spelfönstret
    public GUI() {
        this.setSize(width + 6, height + 29); //6 och 29 läggs till för att vi inte ska ha någon spelgrafik på fönstrets kanter
        this.setTitle("Johanna's card game - Pyramid Solitaire");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        this.setContentPane(board);
    }

    public class Board extends JPanel {

        public void paint(Graphics g) {
            g.setColor(backgroundColor);
            g.fillRect(0, 0, width, height);

            //här ska det som hör till menyn finnas (knappar etc)
            if (state == State.MENU) {

            }

            //här ska det som hör till spelet finnas (kortuppritning, knappar etc)
            else if (state == State.GAME) {

            }

            //här ska grafiken som visas om man vinner finnas + highscore?
            else if (state == State.WIN) {

            }

        }

    }
}
