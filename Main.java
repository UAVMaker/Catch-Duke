
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darvin
 */
public class Main {
 public static void main(String[] args){
     JFrame random = new JFrame("Catch the Creature");
     random.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     random.getContentPane().add(new RandomImage(1000));
     random.pack();
     random.setVisible(true);
 }   
}
