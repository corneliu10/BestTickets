package service;

import javafx.util.Pair;
import logging.Logger;
import model.Artist;
import model.Client;
import model.Event;
import sun.rmi.runtime.Log;
import utilities.CSVUtilities;
import utilities.ConnectionUtilities;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventService extends CSVUtilities<Event> {
    private static final String FILENAME = "events.csv";

    private static List<Event> events = new ArrayList<>();
    private static EventService instance = new EventService();

    private ConnectionUtilities connectionUtilities = ConnectionUtilities.getInstance();

    private EventService() {
//        Artist artist = new Artist("Martin", "Garrix", 20, 1);
//        Client client = ClientService.getInstance().createClient("John", "Witzky", 21);
//        createEvent("Untold", "Cluj-Napoca", new Date(2019, 8, 23),
//                              new Date(2019, 9, 27), 400);
//        events.get(0).addArtist(artist);
//        events.get(0).buyNewTicket(client);
        if (!CSVService.fileExists(FILENAME)) {
            CSVService.createHeaderFile(FILENAME, new String[]{"Title", "Location", "Start Date", "End Date", "Max Tickets", "Id"});
        }
    }

    public static EventService getInstance() {
        return instance;
    }

    public Event createEvent(String title, String location, Date startDate,
                             Date endDate, int maxTickets, boolean verbose) {
        return createEvent(title, location, startDate, endDate, maxTickets, Event.nrOfEvents, verbose);
    }

    public Event createEvent(String title, String location, Date startDate, Date endDate, int maxTickets) {
        return createEvent(title, location, startDate, endDate, maxTickets, Event.nrOfEvents, false);
    }

    public Event createEvent(String title, String location, Date startDate,
                             Date endDate, int maxTickets, int id, boolean verbose) {
        Event event = new Event(title, location, startDate, endDate, maxTickets, id);
        events.add(event);
        Logger.getInstance().info("Event " + id + " created!", verbose);
        return event;
    }

    public Event createEvent(String[] attributes) {
        String title = attributes[0];
        String location = attributes[1];
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = CSVService.dateFormatter.parse(attributes[2]);
            endDate = CSVService.dateFormatter.parse(attributes[3]);
        } catch (ParseException e) {
            Logger.getInstance().error(e.getMessage());
            e.printStackTrace();
        }
        int maxTickets = Integer.parseInt(attributes[4]);
        int eventId = Integer.parseInt(attributes[5]);

        Event event = new Event(title, location, startDate, endDate, maxTickets, eventId);
        events.add(event);
        return event;
    }

    @Override
    public String[] extractAttributes(Event event) {
        String title = event.getTitle();
        String location = event.getLocation();
        String startDate = CSVService.dateFormatter.format(event.getStartDate());
        String endDate = CSVService.dateFormatter.format(event.getEndDate());
        String maxTickets = String.valueOf(event.getMaxTickets());
        String eventId = String.valueOf(event.getId());

        return new String[]{title, location, startDate, endDate, maxTickets, eventId};
    }

    public void writeToFile(Event obj) {
        super.writeToFile(obj, FILENAME);
    }

    public Event getEventById(int idEvent) {
        for (Event event : events) {
            if (event.getId() == idEvent)
                return event;
        }

        return null;
    }

    public void loadObjects() {
        super.loadObjects(FILENAME, this::createEvent);
    }

    public List<Event> getEvents() {
        String query = "select * from tickets.events;";
        List<Event> events = new ArrayList<>();
        ResultSet rs = connectionUtilities.selectData(query);
        try {
            while (rs.next()) {
                String title = rs.getString("title");
                String location = rs.getString("location");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                int maxTickets = rs.getInt("maxTickets");
                int idEvent = rs.getInt("idEvent");

                events.add(new Event(title, location, startDate, endDate, maxTickets, idEvent));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public void showEvents() {
        getEvents().stream().forEach(System.out::println);
    }

    public void insertEvent(Event obj) {
        String query = "insert into tickets.events(title, location, startDate, endDate, maxTickets) " +
                "values ('" + obj.getTitle() + "','" + obj.getLocation() + "','"
                + ConnectionUtilities.dateFormatter.format(obj.getStartDate()) + "','"
                + ConnectionUtilities.dateFormatter.format(obj.getEndDate()) + "'," + obj.getMaxTickets() + ");";

        connectionUtilities.updateData(query);
    }

    public void showArtists(int eventId) {
        String query = "select ea.idArtist, a.firstName, a.lastName, a.age from tickets.clients a" +
                " inner join tickets.events_artists ea on a.idClient = ea.idArtist " +
                " where ea.idEvent = " + eventId + ";";

        ResultSet rs = connectionUtilities.selectData(query);
        try {
            while (rs.next()) {
                String firstName = rs.getString("a.firstName");
                String lastName = rs.getString("a.lastName");
                int age = rs.getInt("a.age");
                int idArtist = rs.getInt("ea.idArtist");

                System.out.println(new Artist(firstName, lastName, age, idArtist).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
