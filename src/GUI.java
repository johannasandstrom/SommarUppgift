import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame{

    int width = 1400;
    int height = 750;

    public GUI() {
        this.setSize(width + 6, height + 29); //6 och 29 läggs till för att vi inte ska ha någon spelgrafik på fönstrets kanter
        this.setTitle("Johanna's card game");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
