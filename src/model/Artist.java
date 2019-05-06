package model;

import logging.Logger;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Artist extends Person {
    private SortedSet<Event> upcomingEvents;

    public Artist(String firstName, String lastName, int age, int id) {
        super(firstName, lastName, age, id);
        upcomingEvents = new TreeSet<>(Comparator.comparing(Event::getStartDate));
    }

    public void addEvent(Event event) {
        Logger.getInstance().info("Added event " + event.getId() + " to artist " + this.getId());
        this.upcomingEvents.add(event);
    }

    public Set<Event> getUpcomingEvents() {
        return upcomingEvents;
    }
}
