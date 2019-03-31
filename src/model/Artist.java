package model;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Artist extends Person {
    private SortedSet<Event> upcomingEvents;

    public Artist(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        upcomingEvents = new TreeSet<>(Comparator.comparing(Event::getStartDate));
    }

    public void addEvent(Event event) {
        this.upcomingEvents.add(event);
    }

    public Set<Event> getUpcomingEvents() {
        return upcomingEvents;
    }
}
