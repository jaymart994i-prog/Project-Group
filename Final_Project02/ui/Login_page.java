package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login_page {
    private JPanel Panels;
    private JLabel userlable;
    private JLabel passlabel;
    private JTextField user;
    private JPasswordField password;
    private JButton Login;
    private JButton registerBtn;
    private JFrame frame;
    private MovieStore store;

    public Login_page(MovieStore store) {
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
                    JOptionPane.showMessageDialog(null, "Please Input Username or Password");
                } else {
                    boolean isLoginSuccess = false;
                    try {
                        FileReader fr = new FileReader("src/ui/users.txt");
                        BufferedReader br = new BufferedReader(fr);
                        String line;

                        while ((line = br.readLine()) != null) {
                            String[] data = line.split(",");
                            if (data.length == 2) {
                                if (user.getText().equals(data[0]) && password.getText().equals(data[1])) {
                                    isLoginSuccess = true;
                                    break;
                                }
                            }
                        }
                        br.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (isLoginSuccess) {
                        frame.dispose();
                        new main_page(store);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Register_page(store);
            }
        });
    }
}