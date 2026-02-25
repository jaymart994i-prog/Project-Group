package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rent_page {
    JFrame frame = new JFrame("RENT & BUY");
    JPanel main = new JPanel();
    MovieStore store;

    public Rent_page(MovieStore store) {
        this.store = store;
        frame.setSize(640, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        main.setBackground(new Color(250, 250, 250));

        JLabel title = new JLabel("OUR FILM");
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        Font actionFont = new Font("SansSerif", Font.PLAIN, 13);

        String[] films = new String[store.getItemCount()];
        for (int i = 0; i < store.getItemCount(); i++) {
            films[i] = store.getItems()[i].getId() + " - " + store.getItems()[i].getTitle() + " [" + store.getItems()[i].getStatus() + "]";
        }

        JList<String> filmList = new JList<>(films);
        filmList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        filmList.setBackground(new Color(250, 250, 250));
        filmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        filmList.setFixedCellHeight(36);

        filmList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selected = filmList.getSelectedValue();
                if (selected == null) return;

                String[] parts = selected.split(" - ");
                String selectedId = parts[0];


                double rPrice = 0.0;
                double bPrice = 0.0;
                for (int i = 0; i < store.getItemCount(); i++) {
                    if (store.getItems()[i].getId().equals(selectedId)) {

                        if (store.getItems()[i] instanceof Movie) {
                            Movie m = (Movie) store.getItems()[i];
                            rPrice = m.getRentPrice();
                            bPrice = m.getBuyPrice();
                        }
                    }
                }

                JPanel dialogPanel = new JPanel();
                dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
                dialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel infoTitle = new JLabel(selected);
                infoTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
                infoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel infoSub = new JLabel("Rent: " + rPrice + " Baht  |  Buy: " + bPrice + " Baht");
                infoSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
                infoSub.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton rentBtn = new JButton("RENT");
                rentBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                rentBtn.setFont(actionFont);

                final double finalRPrice = rPrice;
                rentBtn.addActionListener(ev -> {

                    int confirm = JOptionPane.showConfirmDialog(frame, "Confirm renting this movie for " + finalRPrice + " Baht?", "Confirm Rent", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean success = store.processRent(selectedId);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Success! You rented " + selectedId + " for " + finalRPrice + " Baht.", "Receipt", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed! Movie is already Rented or Sold.", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        SwingUtilities.getWindowAncestor(rentBtn).dispose();
                        frame.dispose();
                        new Rent_page(store);
                    }
                });

                JButton buyBtn = new JButton("BUY");
                buyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);


                final double finalBPrice = bPrice;
                buyBtn.addActionListener(ev -> {

                    int confirm = JOptionPane.showConfirmDialog(frame, "Confirm buying this movie for " + finalBPrice + " Baht?", "Confirm Buy", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean success = store.processBuy(selectedId);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Success! You purchased " + selectedId + " for " + finalBPrice + " Baht.", "Receipt", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed! Movie is already Rented or Sold.", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                        SwingUtilities.getWindowAncestor(buyBtn).dispose();
                        frame.dispose();
                        new Rent_page(store);
                    }
                });

                dialogPanel.add(infoTitle);
                dialogPanel.add(Box.createVerticalStrut(5));
                dialogPanel.add(infoSub);
                dialogPanel.add(Box.createVerticalStrut(15));
                dialogPanel.add(rentBtn);
                dialogPanel.add(Box.createVerticalStrut(8));
                dialogPanel.add(buyBtn);

                JOptionPane.showMessageDialog(frame, dialogPanel, "Film Detail", JOptionPane.PLAIN_MESSAGE);
            }
        });

        JScrollPane scroll = new JScrollPane(filmList);
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        JButton backBtn = new JButton("BACK TO MAIN PAGE");
        backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            frame.dispose();
            new main_page(store);
        });

        main.add(title);
        main.add(Box.createVerticalStrut(20));
        main.add(scroll);
        main.add(Box.createVerticalStrut(20));
        main.add(backBtn);

        frame.setContentPane(main);
        frame.setVisible(true);
    }
}