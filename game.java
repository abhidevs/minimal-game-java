import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class game {
    static final int x=300;
    static final int y=500;
    static JFrame mainFrame;

    public game(mainPanel panel){
        mainFrame=new JFrame();
        mainFrame.add(panel);
        mainFrame.setSize(x,y);
        mainFrame.setVisible(true);
    }

    public static void main(String []args){
        mainPanel panel=new mainPanel(10,0);
        new game(panel);
        spawnBalls balls=new spawnBalls(panel);
        balls.run();
    }
}


class mainPanel extends JPanel{
    int x,y;

    public mainPanel(int x,int y){
        this.x=x;
        this.y=y;

        Timer timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBall();
                repaint();
            }
        });
        timer.start();
    }

    // Updating y axis of ball every 40ms
    protected void moveBall() {
        y++;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        // g.drawOval(10, 10, 35, 35);
        g.fillOval(x, y, 30, 30);
        // setDropping(g);
    }

    //wrong logic feel free to remove this
    public void setDropping(Graphics g){
        int mY=(int)g.getClipBounds().getY();
        for(int i=1;i<=50000000;i++){
            g.translate((int)g.getClipBounds().getX(),mY++);
        }
    }
}

class spawnBalls implements Runnable{

    JPanel mpanel;

    public spawnBalls(JPanel panel) {
        mpanel=panel;
    }

    @Override
    public void run() {
    }
}