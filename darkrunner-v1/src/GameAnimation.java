import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameAnimation extends JPanel implements ActionListener{

    final int PANEL_WIDTH = 1200;
    final int PANEL_HEIGHT = 480;

    int collision = 0;
    int score = 0;

    double random; //the randomizer for the coordinates of the obstacles
    int difficultyBarrier = 500; //the inital score after which the difficulty increases

    //BACKGROUND CONSTANTS
    int backgroundX = 0;
    int backgroundY = 0;
    int backgroundXVelocity = 2;

    //DARK RUNNER CONSTANTS
    int runnerX = 30;
    int runnerY = 288;

    //GROUND OBSTACLE CONSTANTS
    int groundObstacleX = -100;
    int groundObstacleY = 335;
    int groundObstacleXVelocity = 10;

    //AIR OBSTACLE CONSTANTS
    int airObstacleX = -100;
    int airObstacleY = 228;
    int airObstacleXVelocity = 10;

    //KEY BIND OBJECTS
    Action upAction;
    Action downAction;

    //IMAGE 
    Image background;
    Image runner;
    Image platform;
    Image healthFull;
    Image healthTwo;
    Image healthOne;
    Image healthEmpty;
    Image healthDraw;
    Image groundObstacle;
    Image airObstacle;
    Image gameoverScreen;
    
    //TIMER OBJECT
    Timer timer;

    GameAnimation() {

        background = new ImageIcon("background.png").getImage();
        runner = new ImageIcon("dark_runner.png").getImage();
        platform = new ImageIcon("platform.png").getImage();

        healthFull = new ImageIcon("health_full.png").getImage();
        healthTwo = new ImageIcon("health_two.png").getImage();
        healthOne = new ImageIcon("health_one.png").getImage();
        healthEmpty = new ImageIcon("health_empty.png").getImage();
        healthDraw = healthFull;

        gameoverScreen = new ImageIcon("gameoverscreen.png").getImage();

        groundObstacle = new ImageIcon("barrier.png").getImage();
        airObstacle = new ImageIcon("star.png").getImage();

        //SETTING UP THE UP KEY BIND
        upAction = new UpAction();
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        this.getActionMap().put("upAction", upAction);

        //SETTING UP THE DOWN KEY BIND
        downAction = new DownAction();
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        this.getActionMap().put("downAction", downAction);


        this.setPreferredSize(new Dimension(1200, 480));

        //SETTING UP THE TIMER
        timer = new Timer(5, this);
        timer.start(); //calling the timer to initiate the ActionPerformed
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background, backgroundX, backgroundY, null);
        g2D.drawImage(runner, runnerX, runnerY, null);
        g2D.drawImage(groundObstacle, groundObstacleX, groundObstacleY, null);
        g2D.drawImage(airObstacle, airObstacleX, airObstacleY, null);
        g2D.drawImage(platform, 0 ,385, null);
        g2D.drawImage(healthDraw, 1070, 430, null);

        //COLLISION CONSEQUENCES (HEALTH DECREASE)
        if(collision<=3){

            if(collision<3){

                //SCORE
                score++; //score increments as long as health is available

                g2D.setPaint(Color.WHITE);
                g2D.setFont(new Font("Konstruktor", Font.PLAIN, 27));
                g2D.drawString("SCORE: " + (int)score/10, 1040, 50);

                if (((int)score/10)==difficultyBarrier) { //if score goes above a difficultyBarrier, the difficulty increases

                    backgroundXVelocity+=2;
                    difficultyBarrier+=500;

                    System.out.println("\nDIFFICULTY +1");
                }
            }

            if(collision==3){ //if all the health is used up

                g2D.drawImage(gameoverScreen,-18,0,null); //game over screen shown

                g2D.setPaint(Color.WHITE);
                g2D.setFont(new Font("Konstruktor", Font.PLAIN, 40));
                g2D.drawString("FINAL SCORE: " + (int)score/10, 440, 450); //along with final score

                System.out.println("GAME OVER\n"); //game over is also printed to the console
                
                collision+=1;
            }
        }
    }

    //TIMER'S ACTION LISTENER
    public void actionPerformed(ActionEvent e){

        if (backgroundX<=-855){ //if the background image moves to the left a certain amount, it resets, giving the illusion on an infinite loop

            backgroundX=0;
        }

        backgroundX = backgroundX-backgroundXVelocity; //background animation

        if(groundObstacleX<=-1000){ //generation of ground obstacle

            random = Math.random();

            groundObstacleX = 3000+10*(int)(random*100);
        }

        if(airObstacleX<=-1000){ //generation of air obstacle

            random = Math.random();

            airObstacleX = 4000+10*(int)(random*100);
        }

        if(groundObstacleX>airObstacleX){ //condition to make them generate a certain space apart

            if(groundObstacleX-airObstacleX<=250) {

                groundObstacleX+=250;
            }
        }

        if(airObstacleX>groundObstacleX){ //condition to make them generate a certain space apart

            if(airObstacleX-groundObstacleX<=250) {

                airObstacleX+=250;
            }
        }

        groundObstacleX = groundObstacleX-groundObstacleXVelocity; //animation of obstacle animation

        airObstacleX = airObstacleX-airObstacleXVelocity; //animation of obstacle animation

        if(groundObstacleX==runnerX && runnerY>232){

            collision++; //if running and barrier's x & y coordinates are same , they collide, collision increments
            System.out.println("\nCOLLISION! (with Ground Obstacle)\nHEALTH -1\n");
        }

        if(airObstacleX==runnerX && runnerY<268){
            
            collision++; //if running and star's x & y coordinates are same , they collide, collision increments
            System.out.println("\nCOLLISION! (with Air Obstacle)\nHEALTH -1\n");
        }

        //HEALTH BAR
        if(collision==1) {

            healthDraw=healthTwo;
        }

        if(collision==2) {

            healthDraw=healthOne;
        }
        
        if(collision==3) {

            healthDraw=healthEmpty;
        }

        if(collision<=3) {

            repaint();
        }
        if(collision>3) {

            timer.stop();
        }

    }

    //DEFINITION OF UPACTION CLASS (THE UP KEYBIND)
    public class UpAction extends AbstractAction {

        //IF UP IS PRESSED, THIS ACTION IS PERFORMED
        public void actionPerformed(ActionEvent e) {

            runnerY = runnerY-20; //the y-coordinate of runner decreases by 10 pixels (so it goes up)

            if (runnerY<=228){

                runnerY = 228; //makes sure the runner cannot go above a certain distance
            }
        }
    }

    //DEFINITION OF DOWNACTION CLASS (THE DOWN KEYBIND)
    public class DownAction extends AbstractAction {

        //IF DOWN IS PRESSED, THIS ACTION IS PERFORMED
        public void actionPerformed(ActionEvent e) {

            runnerY = runnerY+20; //the y-coordinate of runner increases by 10 pixels (so it goes down)

            if (runnerY>=288) {

                runnerY = 288; //makes sure the runner cannot go below a certain distance (the platform)
            }
        }
    }
}