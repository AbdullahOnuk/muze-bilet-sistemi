public class FullTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket(String visitorName, double basePrice) {
        return new FullTicket(visitorName, basePrice);
    }
}