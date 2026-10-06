public class StudentTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket(String visitorName, double basePrice) {
        return new StudentTicket(visitorName, basePrice);
    }
}