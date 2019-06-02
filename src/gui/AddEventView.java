package gui;

import model.Event;
import service.EventService;

import javax.swing.*;
import java.util.Date;

public class AddEventView {
    JFrame frame = new JFrame("Add event");

    JLabel titleLbl = new JLabel("Title: ");
    JLabel locationLbl = new JLabel("Location: ");
    JLabel maxTicketsLbl = new JLabel("Max Tickets: ");

    JTextField titleTxt = new JTextField();
    JTextField locationTxt = new JTextField();
    JTextField maxTicketsTxt = new JTextField();

    JButton addEventBtn = new JButton("Add event");

    AddEventView() {
        titleLbl.setBounds(50, 10, 150, 40);
        titleTxt.setBounds(150, 10, 150, 40);

        locationLbl.setBounds(50, 70, 150, 40);
        locationTxt.setBounds(150, 70, 150, 40);

        maxTicketsLbl.setBounds(50, 130, 150, 40);
        maxTicketsTxt.setBounds(150, 130, 150, 40);

        addEventBtn.setBounds(150, 200, 150, 40);
        addEventBtn.addActionListener(e -> {
            Event event = new Event(titleTxt.getText(), locationTxt.getText(),
                                    new Date(), new Date(), Integer.parseInt(maxTicketsTxt.getText()), -1);
            EventService.getInstance().insertEvent(event);
            frame.setVisible(false);
            frame.dispose();
        });

        frame.add(titleLbl);
        frame.add(locationLbl);
        frame.add(maxTicketsLbl);
        frame.add(titleTxt);
        frame.add(locationTxt);
        frame.add(maxTicketsTxt);
        frame.add(addEventBtn);

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
