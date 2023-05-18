package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OvertlySillyGUI extends JFrame {
    private final JFrame frame;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;

    public OvertlySillyGUI() {
//        create and set up the window
        frame = new JFrame("Noodle - Login");
        setTitle("Silly Chat");
        setContentPane(panel1);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


//        super("Silly Chat App");
//        setSize(400, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        panel1.setVisible(true);
    }
}




