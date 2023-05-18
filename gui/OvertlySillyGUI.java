package gui;

import javax.swing.*;
import java.awt.*;

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

        // add padding to the text field
        textField1.setBorder(BorderFactory.createCompoundBorder(
                textField1.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // make the button not ugly
        button1.setBorderPainted(false);
//        button1.setFocusPainted(false);
//        button1.setContentAreaFilled(false);
        button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}




