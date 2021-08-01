package ball;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball {

    private Color color;
    private Point location;
    private Dimension size;
    private String ballValue; 

    public Ball(Color color) {

        setColor(color);
        size = new Dimension(35, 35);

    }

    public void setBallValue(String s){
        ballValue=s;
    }

    public String getBallValue(){
        return ballValue;
    }

    public void setSize(int width, int height) {
        this.size = new Dimension(width, height);
    }

    public Dimension getSize() {
        return size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public Point getLocation() {
        return location;
    }

    public void paint(Graphics2D g2d) {

        Point p = getLocation();
        if (p != null) {
            g2d.setColor(getColor());
            Dimension size = getSize();
            g2d.fillOval(p.x, p.y, size.width, size.height);
            g2d.setColor(Color.white);
            g2d.drawString(getBallValue(), p.x+12, p.y+20);
        }

    }
}
