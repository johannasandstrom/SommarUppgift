import javax.swing.*;

public class GUI extends JFrame {

    //Grafiken för spelet - skapar spelfönstret
    public GUI(Board board) {
        this.setSize(board.getWidth() + 6, board.getHeight() + 29); //6 och 29 läggs till för att vi inte ska ha någon spelgrafik på fönstrets kanter
        this.setLocationRelativeTo(null);
        this.setTitle("Pyramiden");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.getContentPane().add(board);

    }
}
