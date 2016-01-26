
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author preej0747
 */
public class game extends JComponent implements KeyListener, MouseListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //player position variables
    int x = 100;
    int y = 500;
    int mouseX = 0;
    int mouseY = 0;
    boolean buttonpressed = false;
    //block
    ArrayList<Rectangle> blocks = new ArrayList<>();
    ArrayList<Rectangle> fatcap = new ArrayList<>();
    //player
    Rectangle player = new Rectangle(0, 475, 50, 50);
    int moveX = 0;
    int moveY = 0;
    boolean inAir = false;
    //level 1 complete
    Rectangle levelOne = new Rectangle(1250, 325, 150, 150);
    //level 2 complete
    Rectangle levelTwo = new Rectangle(2250, 325, 150, 150);
    //title screen
    Rectangle titleScreen = new Rectangle(0, 0, 800, 600);
    //death screen
    Rectangle deathscreen = new Rectangle(0, 0, 800, 600);
    //objective
    Rectangle smallloan = new Rectangle(3050, 325, 50, 50);
    //instructions
    Rectangle howToPlay = new Rectangle(0, 0, 800, 600);
    //winning screen
    Rectangle youwin = new Rectangle(0, 0, 800, 600);
    //background
    Rectangle backGround = new Rectangle(0, 0, 10000, 600);
    //set gravity
    int gravity = 1;
    //keyboard variables
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    boolean jump = false;
    boolean switchScreens = false;
    //camera
    int camx = 0;
    //create variable to switch screens
    int screen = 0;
    //add pictures
    BufferedImage donaldtrump = loadImage("donaldtrump.jpg");
    BufferedImage background = loadImage("background.jpg");
    BufferedImage level1 = loadImage("level1.png");
    BufferedImage level2 = loadImage("level2.png");
    BufferedImage title = loadImage("donaldtrumpgame.png");
    BufferedImage deathScreen = loadImage("game over.png");
    BufferedImage smallLoan = loadImage("money.png");
    BufferedImage instructions = loadImage("how to play.png");
    BufferedImage win = loadImage("youWin.png");

    public BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (Exception e) {
            System.out.println("Error loading " + filename);
        }
        return img;
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 



        //screens
        //title screen
        if (screen == 0) {
            g.drawImage(title, titleScreen.x, titleScreen.y, titleScreen.width, titleScreen.height, null);
        }

        //instruction screen
        if (screen == 1) {
            g.drawImage(instructions, howToPlay.x, howToPlay.y, howToPlay.width, howToPlay.height, null);
        }

        //game
        if (screen == 2) {

            //background
            g.drawImage(background, backGround.x - camx, backGround.y, backGround.width, backGround.height, null);

            //level 1 complete
            g.drawImage(level1, levelOne.x - camx, levelOne.y, levelOne.height, levelOne.width, null);

            //level 2 complete
            g.drawImage(level2, levelTwo.x - camx, levelTwo.y, levelTwo.height, levelTwo.width, null);

            //objective
            g.drawImage(smallLoan, smallloan.x - camx, smallloan.y, smallloan.height, smallloan.width, null);


            //player
            g.drawImage(donaldtrump, player.x - camx, player.y, player.width, player.height, null);

            //set color to orange
            Color orange = new Color(232, 145, 5);
            g.setColor(orange);

            //go through each block
            for (Rectangle block : blocks) {
                g.fillRect(block.x - camx, block.y, block.width, block.height);

            }

            for (Rectangle block : fatcap) {
                g.fillRect(block.x - camx, block.y, block.width, block.height);
            }
        }
        //death screen
        if (screen == 3) {
            g.drawImage(deathScreen, deathscreen.x, deathscreen.y, deathscreen.width, deathscreen.height, null);
        }

        //win screen
        if (screen == 4) {
            g.drawImage(win, youwin.x, youwin.y, youwin.width, youwin.height, null);
        }



        //program it so the camera does not run while the title or instruction screen is up
        if (screen == 0 || screen == 1) {
            camx = 0;
        }

        //when the player reaches the objective they win
        if (player.x > 3050 || player.x < 3100 && player.y == 325) {
            screen = 4;
        }
    }
    // GAME DRAWING ENDS HERE

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {

        //initial things to do before game starts
        //add blocks
        //platform
        blocks.add(new Rectangle(0, 550, 100000, 50));
        
        //level 1
        fatcap.add(new Rectangle(400, 525, 25, 25));
        fatcap.add(new Rectangle(600, 525, 25, 25));
        fatcap.add(new Rectangle(800, 525, 25, 25));
        fatcap.add(new Rectangle(1000, 525, 25, 25));
        fatcap.add(new Rectangle(1200, 525, 25, 25));

        //level 2
        fatcap.add(new Rectangle(1600, 525, 25, 25));
        fatcap.add(new Rectangle(1750, 525, 25, 25));
        fatcap.add(new Rectangle(1900, 525, 25, 25));
        fatcap.add(new Rectangle(2050, 525, 25, 25));
        fatcap.add(new Rectangle(2200, 525, 25, 25));

        //level 3
        fatcap.add(new Rectangle(2600, 525, 25, 25));
        fatcap.add(new Rectangle(2700, 525, 25, 25));
        fatcap.add(new Rectangle(2800, 525, 25, 25));
        fatcap.add(new Rectangle(2900, 525, 25, 25));
        fatcap.add(new Rectangle(3000, 525, 25, 25));

        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 

            //camera movement
            camx++;

            x = mouseX;
            y = mouseY;

            if (left) {
                moveX = -2;
            } else if (right) {
                moveX = 2;
            } else {
                moveX = 0;
            }

            //double jump
            if (jump == true) {
            }
            //gravity pulling player down
            moveY = moveY + gravity;

            //jumping
            //jump being pressed and not in air
            if (jump && !inAir) {
                moveY = -27;
                inAir = true;

            }

            //move player
            player.x = player.x + moveX;
            player.y = player.y + moveY;

            //if feet of player become lower than the screen
            if (player.y + player.height > HEIGHT) {
                player.y = HEIGHT - player.height;
                moveY = 0;
                inAir = false;
            }

            //go through all the blocks
            for (Rectangle block : blocks) {
                if (player.intersects(block)) {
                    Rectangle intersection = player.intersection(block);

                    //fix x movement
                    if (intersection.width < intersection.height) {
                        if (player.x < block.x) {
                            player.x = player.x - intersection.height;
                        } else {
                            player.x = player.x + intersection.width;
                        }
                    } else {
                        if (player.y > block.y) {
                            player.y = player.y + intersection.height;
                            moveY = 0;
                        } else {
                            player.y = player.y - intersection.height;
                            moveX = 0;
                            inAir = false;
                        }
                    }
                }
            }

            for (Rectangle block : fatcap) {
                if (player.intersects(block)) {
                    Rectangle intersection = player.intersection(block);
                    if (intersection.width < intersection.height) {
                        if (player.x < block.x) {
                            screen = 3;
                        } else {
                            screen = 3;
                        }

                    } else {
                        screen = 3;
                    }
                }
            }

            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        game game = new game();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        //add the listeners
        frame.addKeyListener(game);
        game.addMouseListener(game);

        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            jump = true;
        }

        if (screen == 0 && key == KeyEvent.VK_ENTER) {
            screen = 1;
        } else if (screen == 1 && key == KeyEvent.VK_ENTER) {
            screen = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            jump = false;
        }

    }
    int poop = 123456;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}