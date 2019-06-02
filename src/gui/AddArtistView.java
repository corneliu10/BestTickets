package gui;

import model.Artist;
import service.ArtistService;

import javax.swing.*;

public class AddArtistView {
    JFrame frame = new JFrame("Add artist");

    JLabel firstNameLbl = new JLabel("First Name: ");
    JLabel lastNameLbl = new JLabel("Last Name: ");
    JLabel ageLbl = new JLabel("Age: ");

    JTextField firstNameTxt = new JTextField();
    JTextField lastNameTxt = new JTextField();
    JTextField ageTxt = new JTextField();

    JButton addArtistBtn = new JButton("Add artist");

    AddArtistView() {
        firstNameLbl.setBounds(50, 10, 150, 40);
        firstNameTxt.setBounds(150, 10, 150, 40);

        lastNameLbl.setBounds(50, 70, 150, 40);
        lastNameTxt.setBounds(150, 70, 150, 40);

        ageLbl.setBounds(50, 130, 150, 40);
        ageTxt.setBounds(150, 130, 150, 40);

        addArtistBtn.setBounds(150, 200, 150, 40);
        addArtistBtn.addActionListener(e -> {
            Artist artist = new Artist(firstNameTxt.getText(), lastNameTxt.getText(),
                                       Integer.parseInt(ageTxt.getText()), -1);
            ArtistService.getInstance().insertArtist(artist);
            frame.setVisible(false);
            frame.dispose();
        });

        frame.add(firstNameLbl);
        frame.add(lastNameLbl);
        frame.add(ageLbl);
        frame.add(firstNameTxt);
        frame.add(lastNameTxt);
        frame.add(ageTxt);
        frame.add(addArtistBtn);

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
