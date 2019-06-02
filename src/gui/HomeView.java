package gui;

import javax.swing.*;

public class HomeView {
    JFrame frame = new JFrame("HomeView");
    JButton addClientButton = new JButton("Add client");
    JButton addArtistButton = new JButton("Add artist");
    JButton addEventButton = new JButton("Add event");
    JButton addTicketButton = new JButton("Add ticket");
    JButton assignEventArtist = new JButton("Assign event to artist");
    JButton showClientsButton = new JButton("Show clients");
    JButton showEventsButton = new JButton("Show events");
    JButton showTicketsButton = new JButton("Show tickets");
    JButton showArtistsButton = new JButton("Show artists");

    public HomeView() {
        addClientButton.setBounds(175, 10, 150, 40);
        addArtistButton.setBounds(175, 60, 150, 40);
        addEventButton.setBounds(175, 110, 150, 40);
        addTicketButton.setBounds(175, 160, 150, 40);
        showClientsButton.setBounds(175, 210, 150, 40);
        showEventsButton.setBounds(175, 260, 150, 40);
        showTicketsButton.setBounds(175, 310, 150, 40);
        showArtistsButton.setBounds(175, 360, 150, 40);
        assignEventArtist.setBounds(175, 410, 150, 40);

        addClientButton.addActionListener(e -> {
            AddClientView view = new AddClientView();
        });
        showClientsButton.addActionListener(e -> {
            ShowClientsView view = new ShowClientsView();
        });

        addArtistButton.addActionListener(e -> {
            AddArtistView view = new AddArtistView();
        });
        showArtistsButton.addActionListener(e -> {
            ShowArtistsView view = new ShowArtistsView();
        });

        addEventButton.addActionListener(e -> {
            AddEventView view = new AddEventView();
        });
        showEventsButton.addActionListener(e -> {
            ShowEventsView view = new ShowEventsView();
        });

        addTicketButton.addActionListener(e -> {
            AddTicketView view = new AddTicketView();
        });
        showTicketsButton.addActionListener(e -> {
            ShowTicketsView view = new ShowTicketsView();
        });

        assignEventArtist.addActionListener(e -> {
            AssignEventArtistView view = new AssignEventArtistView();
        });

        frame.add(addClientButton);
        frame.add(addArtistButton);
        frame.add(addEventButton);
        frame.add(addTicketButton);
        frame.add(showClientsButton);
        frame.add(showEventsButton);
        frame.add(showTicketsButton);
        frame.add(showArtistsButton);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
