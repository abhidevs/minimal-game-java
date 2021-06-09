package ball;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
<<<<<<< HEAD

public class Ball {

=======
import java.awt.Image;

public class Ball {

    private Image meteor;
>>>>>>> Meteor-Shower
    private Color color;
    private Point location;
    private Dimension size;
    private String ballValue; 
<<<<<<< HEAD

    public Ball(Color color) {

        setColor(color);
        size = new Dimension(35, 35);

    }
=======
    public Color valueColor;

    public Ball(Image m) {meteor=m;valueColor=Color.white;}
>>>>>>> Meteor-Shower

    public void setBallValue(String s){
        ballValue=s;
    }

    public String getBallValue(){
        return ballValue;
    }

<<<<<<< HEAD
=======
    public void setRock(){
        meteor=null;
    }

>>>>>>> Meteor-Shower
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
<<<<<<< HEAD
            g2d.fillOval(p.x, p.y, size.width, size.height);
            g2d.setColor(Color.white);
            g2d.drawString(getBallValue(), p.x+12, p.y+20);
=======
            
            if(meteor!=null)
            g2d.drawImage(meteor ,p.x, p.y, null);

            if(ballValue!=null)
            g2d.setColor(valueColor);
            g2d.drawString(getBallValue(), p.x+10, p.y+20);
>>>>>>> Meteor-Shower
        }

    }
}
