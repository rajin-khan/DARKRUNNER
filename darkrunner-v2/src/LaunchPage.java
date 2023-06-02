import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LaunchPage implements ActionListener{

    JFrame frame = new JFrame();

    JLabel mainbg = new JLabel();

    JButton button = new JButton();

    LaunchPage() {

        //image icons
        ImageIcon background = new ImageIcon("titlescreen.jpg");
        ImageIcon playButton = new ImageIcon("playbutton.png");

        frame.setSize(1300, 504);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("DARKRUNNER MENU");
        //frame.setLayout(null);

        mainbg.setLayout(null);
        mainbg.setIcon(background);
        mainbg.setOpaque(true);
        mainbg.setHorizontalAlignment(JLabel.CENTER);
        mainbg.setVerticalAlignment(JLabel.CENTER);
        frame.add(mainbg);

        button.setBounds(525, 330, 228, 60);
        button.setIcon(playButton);
        button.addActionListener(this);
        mainbg.add(button);
        
        frame.setVisible(true);      
    }

    public void actionPerformed (ActionEvent e) {

        if (e.getSource()==button) {

            frame.dispose();
            System.out.println("\nLaunch Page closed.");
            new GameWindow();
        }
    }
    
}
