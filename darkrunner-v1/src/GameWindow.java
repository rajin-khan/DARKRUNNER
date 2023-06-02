import javax.swing.*;

public class GameWindow extends JFrame{

    GameAnimation panel;

    GameWindow() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("DARKRUNNER");

        panel = new GameAnimation();

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
        System.out.println("\nNew Game Window Launched.");
    }
}
