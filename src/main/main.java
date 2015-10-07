package main;

import UI.MainUI;
import java.awt.Color;
import javax.swing.JFrame;

public class main {
       
    public static void main(String[] args) {

        JFrame f=new JFrame();      
        MainUI pf = new MainUI();
        f.setLocation(520, 220);
        f.setTitle("Dice Game");
        f.add(pf.Display());
        f.pack();
        f.setSize(230,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }
    
}
