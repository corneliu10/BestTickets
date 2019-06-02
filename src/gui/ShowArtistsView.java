package gui;

import model.Artist;
import service.ArtistService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShowArtistsView {
    JFrame frame = new JFrame("Show Clients");
    List<JLabel> artistsLbls = new ArrayList<>();

    public ShowArtistsView() {
        addHeaderLabels();
        addArtistsToFrame();

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addHeaderLabels() {
        JLabel label1 = new JLabel("Id");
        label1.setBounds(10, 10, 10, 15);

        JLabel label2 = new JLabel("First Name");
        label2.setBounds(24, 10, 70, 15);

        JLabel label3 = new JLabel("Last Name");
        label3.setBounds(120, 10, 70, 15);

        JLabel label4 = new JLabel("Age");
        label4.setBounds(210, 10, 70, 15);

        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
    }

    private void addArtistsToFrame() {
        List<Artist> artists = ArtistService.getInstance().getArtists();
        int y = 25;
        for (Artist c : artists) {
            JLabel label = new JLabel(String.valueOf(c.getId()));
            label.setBounds(10, y, 10,  20);

            JTextField textField = new JTextField(c.getFirstName());
            textField.setBounds(24, y, 80, 20);

            JTextField textField1 = new JTextField(c.getLastName());
            textField1.setBounds(120, y, 80, 20);

            JTextField textField2 = new JTextField(String.valueOf(c.getAge()));
            textField2.setBounds(210, y, 40, 20);

            JButton btn = new JButton("Save");
            btn.setBounds(260, y, 70, 20);
            btn.addActionListener(e -> {
                ArtistService.getInstance().updateArtist(textField.getText(), textField1.getText(),
                        Integer.parseInt(textField2.getText()), c.getId());
            });

            JButton btn1 = new JButton("Delete");
            btn1.setBounds(340, y, 70, 20);
            btn1.addActionListener(e -> {
                ArtistService.getInstance().removeArtist(c.getId());
                frame.getContentPane().removeAll();
                addArtistsToFrame();
                frame.repaint();
            });

            frame.add(btn);
            frame.add(btn1);
            frame.add(label);
            frame.add(textField);
            frame.add(textField1);
            frame.add(textField2);

            y += 30;
        }
    }
}
