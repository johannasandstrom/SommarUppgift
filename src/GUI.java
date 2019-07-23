import javax.swing.*;

class GUI extends JFrame {

    //Skapar utseendet hos rutan som vårt spel visas i (package-private/varken private, public eller protected)
    GUI(PanelContainer panelContainer) {
        this.setSize(panelContainer.getWidth(),panelContainer.getHeight());
        this.setLocationRelativeTo(null);
        this.setTitle("Pyramiden");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panelContainer);
    }
}
