import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.Math;
public class SierpinskiTriangle extends JPanel implements ActionListener, KeyListener{
    JFrame frame;
    ArrayList<Point> points;
    int xRand, yRand;
    public SierpinskiTriangle(){
        frame = new JFrame("Sierpinski Triangle");
        frame.setSize(800, 600);
        frame.add(this);
        frame.addKeyListener(this);
        points = new ArrayList<Point>();
        run();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public class Point{
        int x, y;
        Color c;
        public Point(int x, int y, Color c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public Color getColor(){
            return c;
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        for(Point p : points){
            g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(), 2, 2);
        }
    }
    public void run(){
        points = new ArrayList<Point>();
        points.add(new Point(400, 100, Color.WHITE));
        points.add(new Point(100, 500, Color.WHITE));
        points.add(new Point(700, 500, Color.WHITE));

        int[] xValues = new int[points.size()];
        int[] yValues = new int[points.size()];
        for(int i = 0; i<points.size(); i++){
            xValues[i] = points.get(i).getX();
            yValues[i] = points.get(i).getY();
        }

        Polygon triangle = new Polygon(xValues, yValues, 3);

        do{
            xRand = (int)(Math.random()*800);
            yRand = (int)(Math.random()*600);
        }while(!triangle.contains(xRand, yRand));

        points.add(new Point(xRand, yRand, Color.WHITE));
    }

    public void drawFract(int n){
        for(int i = 0; i < n; i++){
            int corner = (int)(Math.random()*3);
            points.add(new Point((points.get(corner).getX()+points.get(points.size()-1).getX())/2, (points.get(corner).getY()+points.get(points.size()-1).getY())/2, Color.WHITE));
        }
    }

    public static void main(String[] args){
        SierpinskiTriangle app = new SierpinskiTriangle();
    }

    public void actionPerformed(ActionEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        //If a number is pressed, it adds that many points (Step 9)
        if(e.getKeyCode() >= 49 && e.getKeyCode() <= 57) {
            drawFract(e.getKeyCode() - 48);
        }
        else{
            drawFract(10);
        }
        repaint();
    }

    public void keyTyped(KeyEvent e){

    }
}
