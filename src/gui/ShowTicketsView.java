package gui;

import model.Artist;
import model.Client;
import model.Ticket;
import service.ArtistService;
import service.ClientService;
import service.TicketService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTicketsView {
    JFrame frame = new JFrame("Tickets");
    List<JLabel> ticketsLbls = new ArrayList<>();

    public ShowTicketsView() {
        addHeaderLabels();
        addTicketsToFrame();

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addHeaderLabels() {
        JLabel label1 = new JLabel("Id");
        label1.setBounds(10, 10, 40, 15);

        JLabel label2 = new JLabel("Price");
        label2.setBounds(24, 10, 70, 15);

        JLabel label3 = new JLabel("Event ID");
        label3.setBounds(120, 10, 70, 15);

        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
    }

    private void addTicketsToFrame() {
        List<Ticket> tickets = TicketService.getInstance().getTickets();
        int y = 25;
        for (Ticket c : tickets) {
            JLabel label = new JLabel(String.valueOf(c.getId()));
            label.setBounds(10, y, 10,  20);

            JTextField textField = new JTextField(String.valueOf(c.getPrice()));
            textField.setBounds(24, y, 80, 20);

            JTextField textField2 = new JTextField(String.valueOf(c.getIdEvent()));
            textField2.setBounds(120, y, 40, 20);

            JButton btn = new JButton("Save");
            btn.setBounds(180, y, 70, 20);
            btn.addActionListener(e -> {
                TicketService.getInstance().updateTicket(c.getId(), Integer.parseInt(textField.getText()),
                                                         Integer.parseInt(textField2.getText()));
            });

            JButton btn1 = new JButton("Delete");
            btn1.setBounds(260, y, 70, 20);
            btn1.addActionListener(e -> {
                TicketService.getInstance().removeTicket(c.getId());
                frame.getContentPane().removeAll();
                addTicketsToFrame();
                frame.repaint();
            });

            frame.add(btn);
            frame.add(btn1);
            frame.add(label);
            frame.add(textField);
            frame.add(textField2);

            y += 30;
        }
    }
}
