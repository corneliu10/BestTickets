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
    List<JLabel> eventsLbls = new ArrayList<>();

    public ShowEventsView() {
        addEventsToFrame();

        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void addEventsToFrame() {
        List<Event> events = EventService.getInstance().getEvents();
        int y = 10;
        for (Event c : events) {
            JLabel label = new JLabel(String.valueOf(c.getId()));
            label.setBounds(10, y, 10,  20);

            JTextField title = new JTextField(c.getTitle());
            title.setBounds(24, y, 80, 20);

            JTextField location = new JTextField(c.getLocation());
            location.setBounds(120, y, 80, 20);



            JTextField maxTickets = new JTextField(String.valueOf(c.getMaxTickets()));
            maxTickets.setBounds(210, y, 40, 20);

            JButton btn = new JButton("Save");
            btn.setBounds(260, y, 70, 20);
            btn.addActionListener(e -> {
                ArtistService.getInstance().updateArtist(title.getText(), location.getText(),
                        Integer.parseInt(maxTickets.getText()), c.getId());
            });

            JButton btn1 = new JButton("Delete");
            btn1.setBounds(340, y, 70, 20);
            btn1.addActionListener(e -> {
                ArtistService.getInstance().removeArtist(c.getId());
                frame.getContentPane().removeAll();
                addEventsToFrame();
                frame.repaint();
            });

            frame.add(btn);
            frame.add(btn1);
            frame.add(label);
            frame.add(title);
            frame.add(location);
            frame.add(maxTickets);

            y += 30;
        }
    }
}
