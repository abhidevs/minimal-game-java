import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ball.*;

/*So now the file structure is
  |  
  |
  --Balls Jpanel
  |
  --interface for scoreboard
  |
  -- game class
  
*/

 class Balls extends JPanel {

    scoreCallBack scoreCallBack;

    private List<Ball> ballsUp;
    public int ballsCount = 6;

    public Balls(scoreCallBack callBack) {
        this.scoreCallBack=callBack;

        ballsUp = new ArrayList<Ball>(ballsCount);

        for (int index = 0; index < ballsCount; index++) {
            Ball ball=new Ball(new Color(random(255), random(255), random(255)));
            ball.setBallValue(String.valueOf(random(50)));
            ballsUp.add(ball);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                for (Ball ball : ballsUp) {
                    if (verifyBallClick(ball, me.getPoint())) {
                        scoreCallBack.ballClicked(ball);
                      }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawString(String.valueOf("Score: "+scoreCallBack.getScore()), 10, 15);
        g2d.drawString(String.valueOf("Remaining Clicks: "+scoreCallBack.getRemClicks()), 260, 15);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Ball ball : ballsUp) {
            ball.paint(g2d);
        }
        g2d.dispose();
    }

    public List<Ball> getBalls() {
        return ballsUp;
    }

    private boolean verifyBallClick(Ball ball, Point point) {
        return (ball.getLocation().x <= point.x && ball.getLocation().x+ball.getSize().width >= point.x) && (ball.getLocation().y <= point.y && ball.getLocation().y+ball.getSize().height >= point.y);
    }

    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
}

interface scoreCallBack{
    public void ballClicked(Ball ball);
    public int getScore();
    public int getRemClicks();
}

public class game implements scoreCallBack{

    static game Game;
    static int score;
    static int clicks=1;
    Thread engine;
    JFrame frame;

    public static void main(String[] args) {
        Game=new game();
    }

    public game() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                frame = new JFrame("Ball Dropping Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                scoreCallBack sBack=Game;
                Balls balls = new Balls(sBack);
                frame.add(balls);
                frame.setSize(400, 720);
                frame.setVisible(true);

                engine=new Thread(new DropEngine(balls));
                engine.start();

            }
        });
    }

    @Override
    public void ballClicked(Ball ball){
        if(setRemClicks())
        score+=Integer.valueOf(ball.getBallValue());
        System.out.println("Score "+"+"+ball.getBallValue()+": "+score);
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public int getRemClicks(){
        return clicks;
    }

    boolean setRemClicks(){
        boolean ret;
        if(clicks>0){
            --clicks;
            ret=true;
        }
        else{
            ret=false;
            engine.stop();
            gameOver.run();
        }
        return ret;
    }

    Thread gameOver= new Thread(){
        public void run(){
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    JFrame f=new JFrame("Game Over!");
                    frame.add(f);
                    frame.revalidate();
                }
            });
        }
    };

    public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }

    public class DropEngine implements Runnable {

        private Balls parent;

        public DropEngine(Balls parent) {
            this.parent = parent;
        }

        @Override
        public void run() {

            int width = getParent().getWidth();
            int height = getParent().getHeight();
            int i = 1;

            // Randomize the starting position...
            for (Ball ball : getParent().getBalls()) {
                int x = 10 + random(width - 50);

                int y = 120 * i + random(30);
                i++;

                ball.setLocation(new Point(x, -y));

            }

            while (getParent().isVisible()) {

                // Repaint the balls pen...
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        getParent().repaint();
                    }
                });

                // This is a little dangrous, as it's possible
                // for a repaint to occur while we're updating...
                for (Ball ball : getParent().getBalls()) {
                    if (ball.getLocation().y > height) {
                        int y = -50;
                        int x = 10 + random(width - 50);
                        ball.setLocation(new Point(x, y));
                    }
                    move(ball);
                }

                // Some small delay...
                try {
                    Thread.sleep(12);
                } catch (InterruptedException ex) {
                }

            }

        }

        public Balls getParent() {
            return parent;
        }

        public void move(Ball ball) {

            Point p = ball.getLocation();
            p.y++;

        }
    }
}