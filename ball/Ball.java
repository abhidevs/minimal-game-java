package ball;
import java.awt.*;
import javax.swing.*;
public class Ball {

    private Color color;
    private Point location;
    private Dimension size;
    private String ballValue; 

    public Ball(Color color) {

        setColor(color);
        size = new Dimension(30, 30);

    }

    public void setBallValue(String s){
        ballValue=s;
    }

    public String getBallValue(){
        return ballValue;
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
            g2d.drawString(getBallValue(), p.x+10, p.y+15);
        }

    }
}
