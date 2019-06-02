package gui;

import model.Artist;
import model.Ticket;
import service.ArtistService;
import service.TicketService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTicketsView {
    JFrame frame = new JFrame("Tickets");
    List<JLabel> ticketsLbls = new ArrayList<>();

    public ShowTicketsView() {
        setTicketsLbls();
        addTicketsToFrame();

        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addTicketsToFrame() {
        for (JLabel label : ticketsLbls) {
            frame.add(label);
        }
    }

    private void setTicketsLbls() {
        List<Ticket> tickets = TicketService.getInstance().getTickets();
        int y = 10;
        for (Ticket c : tickets) {
            JLabel label = new JLabel(c.toString());
            label.setBounds(10, y, 400,  20);
            ticketsLbls.add(label);
            y += 30;
        }
    }
}
