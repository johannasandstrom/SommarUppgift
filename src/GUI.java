import javax.swing.*;

public class GUI extends JFrame {

    public GUI(PanelContainer panelContainer) {
        this.setSize(panelContainer.getWidth(),panelContainer.getHeight());
        this.setLocationRelativeTo(null);
        this.setTitle("Pyramiden");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panelContainer);
    }
}
