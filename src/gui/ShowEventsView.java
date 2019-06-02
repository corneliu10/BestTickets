package gui;

import model.Artist;
import model.Event;
import service.ArtistService;
import service.EventService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ShowEventsView {
    JFrame frame = new JFrame("Show Events");

    public ShowEventsView() {
        addHeaderLabels();
        addEventsToFrame();

        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addHeaderLabels() {
        JLabel label1 = new JLabel("Id");
        label1.setBounds(10, 10, 10, 15);

        JLabel label2 = new JLabel("Title");
        label2.setBounds(24, 10, 70, 15);

        JLabel label3 = new JLabel("Location");
        label3.setBounds(120, 10, 70, 15);

        JLabel label4 = new JLabel("Start Date");
        label4.setBounds(210, 10, 70, 15);

        JLabel label5 = new JLabel("End Date");
        label5.setBounds(300, 10, 70, 15);

        JLabel label6 = new JLabel("Max Tickets");
        label6.setBounds(390, 10, 70, 15);

        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
    }

    private void addEventsToFrame() {
        List<Event> events = EventService.getInstance().getEvents();
        int y = 25;
        for (Event c : events) {
            JLabel label = new JLabel(String.valueOf(c.getId()));
            label.setBounds(10, y, 10,  20);

            JTextField title = new JTextField(c.getTitle());
            title.setBounds(24, y, 80, 20);

            JTextField location = new JTextField(c.getLocation());
            location.setBounds(120, y, 80, 20);

            DateTextField startDate = new DateTextField(c.getStartDate());
            startDate.setBounds(210, y, 80, 20);

            DateTextField endDate = new DateTextField(c.getEndDate());
            endDate.setBounds(300, y, 80, 20);

            JTextField maxTickets = new JTextField(String.valueOf(c.getMaxTickets()));
            maxTickets.setBounds(390, y, 70, 20);

            JButton btn = new JButton("Save");
            btn.setBounds(470, y, 70, 20);
            btn.addActionListener(e -> {
                EventService.getInstance().updateEvent(title.getText(), location.getText(), startDate.getDate(),
                                            endDate.getDate(), Integer.parseInt(maxTickets.getText()), c.getId());
            });

            JButton btn1 = new JButton("Delete");
            btn1.setBounds(550, y, 70, 20);
            btn1.addActionListener(e -> {
                EventService.getInstance().removeEvent(c.getId());
                frame.getContentPane().removeAll();
                addEventsToFrame();
                frame.repaint();
            });

            frame.add(btn);
            frame.add(btn1);
            frame.add(label);
            frame.add(startDate);
            frame.add(endDate);
            frame.add(title);
            frame.add(location);
            frame.add(maxTickets);

            y += 30;
        }
    }
}
