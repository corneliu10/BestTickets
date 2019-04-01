import service.EventService;
import service.TicketService;

public class Main {
    public static void main(String[] args) {
        EventService.getInstance().showEvents();
        TicketService.getInstance().showTickets();
        EventService.getInstance().showEvents();
    }
}
