import java.awt.*;
import javax.swing.*;

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
        mainPanel panel=new mainPanel(x,y);
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.blue);
        g.drawOval(10, 10, 35, 35);
        g.fillOval(10, 10, 35, 35);
        setDropping(g);
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