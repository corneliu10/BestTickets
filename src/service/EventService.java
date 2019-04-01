package service;

import model.Artist;
import model.Client;
import model.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventService {
    private static List<Event> events = new ArrayList<>();
    private static EventService instance = new EventService();

    private EventService() {
        Artist artist = new Artist("Martin", "Garrix", 20);
        Client client = ClientService.getInstance().createClient("John", "Witzky", 21);
        createEvent("Untold", "Cluj-Napoca", new Date(2019, 8, 23),
                              new Date(2019, 9, 27), 400);
        events.get(0).addArtist(artist);
        events.get(0).buyNewTicket(client);
    }

    public static EventService getInstance() {
        return instance;
    }

    public Event createEvent(String title, String location, Date startDate, Date endDate, int maxTickets) {
        Event event = new Event(title, location, startDate, endDate, maxTickets);
        events.add(event);
        return event;
    }

    public void showEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public Event getEventById(int idEvent) {
        for (Event event : events) {
            if (event.getId() == idEvent)
                return event;
        }

        return null;
    }
}
