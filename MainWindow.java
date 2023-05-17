import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame {
    private JPanel chatPanel;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public MainWindow() {
        super("Chat App");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        messageField = new JTextField();
        sendButton = new JButton("Send");

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setPreferredSize(new Dimension(400, 50));
        inputPanel.addKeyListener(new MyKeyListener());

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMessage();
            }
        });

        add(chatPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
   

    private void addMessage() {
        chatArea.append(messageField.getText() + "\n\n");
        messageField.setText("");
    }

    class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("AAAAAAAAAAAA" + e.getKeyCode());
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("TESTING");
                addMessage();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());

        }


        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyCode());

        }
    }

}