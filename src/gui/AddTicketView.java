package gui;

import model.Ticket;
import service.TicketService;

import javax.swing.*;

public class AddTicketView {
    JFrame frame = new JFrame("Add ticket");

    JLabel priceLbl = new JLabel("Price: ");
    JLabel eventIDLbl = new JLabel("Event ID: ");

    JTextField priceTxt = new JTextField();
    JTextField eventIDTxt = new JTextField();

    JButton addTicketBtn = new JButton("Add ticket");

    AddTicketView() {
        priceLbl.setBounds(50, 10, 150, 40);
        priceTxt.setBounds(150, 10, 150, 40);

        eventIDLbl.setBounds(50, 70, 150, 40);
        eventIDTxt.setBounds(150, 70, 150, 40);

        addTicketBtn.setBounds(150, 200, 150, 40);
        addTicketBtn.addActionListener(e -> {
            Ticket ticket = new Ticket(Integer.parseInt(priceTxt.getText()), Integer.parseInt(eventIDTxt.getText()), -1);
            TicketService.getInstance().insertTicket(ticket);
            frame.setVisible(false);
            frame.dispose();
        });

        frame.add(priceLbl);
        frame.add(eventIDLbl);
        frame.add(priceTxt);
        frame.add(eventIDTxt);
        frame.add(addTicketBtn);

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
