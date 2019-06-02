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
    JLabel startDateLbl = new JLabel("Start Date:");
    JLabel endDateLbl = new JLabel("End Date:");

    JTextField titleTxt = new JTextField();
    JTextField locationTxt = new JTextField();
    JTextField maxTicketsTxt = new JTextField();
    DateTextField startDate = new DateTextField(new Date());
    DateTextField endDate = new DateTextField(new Date());


    JButton addEventBtn = new JButton("Add event");

    AddEventView() {
        titleLbl.setBounds(50, 10, 150, 40);
        titleTxt.setBounds(150, 10, 150, 40);

        locationLbl.setBounds(50, 70, 150, 40);
        locationTxt.setBounds(150, 70, 150, 40);

        startDateLbl.setBounds(50, 130, 150, 40);
        startDate.setBounds(150, 130, 150, 40);

        endDateLbl.setBounds(50, 190, 150, 40);
        endDate.setBounds(150, 190, 150, 40);

        maxTicketsLbl.setBounds(50, 250, 150, 40);
        maxTicketsTxt.setBounds(150, 250, 150, 40);

        addEventBtn.setBounds(150, 310, 150, 40);
        addEventBtn.addActionListener(e -> {
            Event event = new Event(titleTxt.getText(), locationTxt.getText(),
                                    startDate.getDate(), endDate.getDate(), Integer.parseInt(maxTicketsTxt.getText()), -1);
            EventService.getInstance().insertEvent(event);
            frame.setVisible(false);
            frame.dispose();
        });

        frame.add(titleLbl);
        frame.add(locationLbl);
        frame.add(maxTicketsLbl);
        frame.add(startDateLbl);
        frame.add(startDate);
        frame.add(endDateLbl);
        frame.add(endDate);
        frame.add(titleTxt);
        frame.add(locationTxt);
        frame.add(maxTicketsTxt);
        frame.add(addEventBtn);

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
