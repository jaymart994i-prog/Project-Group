package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class page {
    private JPanel Panels;
    private JLabel userlable;
    private JLabel passlabel;
    private JTextField user;
    private JPasswordField password;
    private JButton Login;
    private JFrame frame;
    private MovieStore store;

    public page(MovieStore store) {
        this.store = store;
        frame = new JFrame("Login");
        frame.setContentPane(Panels);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getText().isEmpty() || password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Input Username and Password");
                } else {

                    frame.dispose();
                    new main_page(store);
                }
            }
        });
    }
}