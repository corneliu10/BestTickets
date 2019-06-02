package gui;

import service.EventArtistsService;

import javax.swing.*;

public class AssignEventArtistView {
    JFrame frame = new JFrame("Assign event to artist");

    JLabel artistIDLbl = new JLabel("Artist ID: ");
    JLabel eventIDLbl = new JLabel("Event ID: ");

    JTextField artistIDTxt = new JTextField();
    JTextField eventIDTxt = new JTextField();

    JButton assignEventBtn = new JButton("Assign event");

    AssignEventArtistView() {
        artistIDLbl.setBounds(50, 10, 150, 40);
        artistIDTxt.setBounds(150, 10, 150, 40);

        eventIDLbl.setBounds(50, 70, 150, 40);
        eventIDTxt.setBounds(150, 70, 150, 40);

        assignEventBtn.setBounds(150, 200, 150, 40);
        assignEventBtn.addActionListener(e -> {
            int eventID = Integer.parseInt(eventIDTxt.getText());
            int artistID = Integer.parseInt(artistIDTxt.getText());

            EventArtistsService.getInstance().addPair(eventID, artistID);
            EventArtistsService.getInstance().insertEventArtist(eventID, artistID);
            frame.setVisible(false);
            frame.dispose();
        });

        frame.add(artistIDLbl);
        frame.add(eventIDLbl);
        frame.add(artistIDTxt);
        frame.add(eventIDTxt);
        frame.add(assignEventBtn);

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
