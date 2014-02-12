package gui;

import sqchat.*;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    
    public Frame() {
        super("testframe");
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 400));
        this.setLayout(new BorderLayout());
        
        System.out.println("Building Frame...");
    }
    
    public void init() {
        this.pack();
        this.setSize(750, 600);    // Sets the size of our frame
        this.setLocationRelativeTo(null);  // Sets the frames location centered (relative to our screen)
        this.setResizable(true);   // Makes the frame resizable
        this.setVisible(true);     // and visible (comes in quite handy at times)
    }
}
