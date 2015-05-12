
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Darvin
 */
class RandomImage extends JPanel {
     private static Random rand = new Random();
    private Timer timer;
    private ImageIcon icon = new ImageIcon("java.png");
    private static int caught = 0, missed = 0;
    private JLabel status = new JLabel("Caught: " + caught + " Missed: " + missed);
    private Creature creature;
    private static JButton start, stop;
    private static boolean running = false;

    public RandomImage(int time) {

        creature = new Creature(icon);
        start = new JButton("Start");

        start.addActionListener(new ButtonListener());
        stop = new JButton("Stop");
        stop.addActionListener(new ButtonListener());

        this.add(start);
        this.add(stop);
        this.add(status);
        status.setForeground(Color.WHITE);
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(800, 500));
        this.addMouseListener(new CreatureFinder());
        int delay = rand.nextInt(time) + time;
        timer = new Timer(delay, new ActionLookOut());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        if (running) {
            super.paintComponent(g);
            creature.draw(g);
        } else {
          
        }
    }

    public void update(boolean isClicked) {
        if (isClicked) {
            this.caught++;
            timer.restart();
            creature.moveCreature();
            repaint();
        } else {
            this.missed++;
            timer.restart();
            creature.moveCreature();
            repaint();
        }
        if (running) {
            String updatedStatus = "Caught: " + caught + " Missed: " + missed;
            this.status.setText(updatedStatus);
        }

    }

    private static class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == start) {
                running = true;
                caught =0;
                missed =0;
                System.out.println("Started");
            } else if (ae.getSource() == stop) {
                running = false; 
                System.out.println("Stop");
            }
        }

    }

    class Creature {

       
        private ImageIcon icon;
        private int xLocation, yLocation;
        private int xMax = 800, yMax = 600;
        private int imageWidth, imageHeight;

        Creature(ImageIcon image) {
            icon = image;
            imageWidth = image.getIconWidth();
            imageHeight = image.getIconHeight();
            xLocation = rand.nextInt(xMax - imageWidth);
            yLocation = rand.nextInt(yMax - imageHeight);

        }

        public void draw(Graphics g) {
            icon.paintIcon(null, g, xLocation, yLocation);
        }

        public void moveCreature() {
            xLocation = rand.nextInt(xMax - imageWidth);
            yLocation = rand.nextInt(yMax - imageHeight);
        }

        public boolean isClickedOn(Point point) {
            Rectangle rect = new Rectangle(xLocation, yLocation, imageWidth, imageHeight);
            return rect.contains(point);
        }

    }

    class CreatureFinder implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            if(running){
            if (creature.isClickedOn(me.getPoint())) {
                update(true);
            }
        else { 
                update(false);
            }
            }
        }

        public void mousePressed(MouseEvent me) {
        }

        public void mouseReleased(MouseEvent me) {
        }

        public void mouseEntered(MouseEvent me) {
        }

        public void mouseExited(MouseEvent me) {
        }
    }

    class ActionLookOut implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if(running){
            update(false);}
        }

    }
}
