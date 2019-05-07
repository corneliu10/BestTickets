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

    public String showUpcomingEvents() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (Event event : upcomingEvents) {
            str.append(event.getId());
            str.append(", ");
        }

        str.append("}, ");
        return str.toString();
    }

    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", upcomingEvents=" + showUpcomingEvents() +
                '}';
    }
}
