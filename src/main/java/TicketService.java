
public class TicketService {

    private final PostgresTicketRepository repository;

    public TicketService(PostgresTicketRepository repository) {
        this.repository = repository;
    }

    public Ticket createAndSaveTicket(TicketFactory factory, String visitorName, double price) {

        Ticket ticket = factory.createTicket(visitorName, price);

        repository.saveTicket(ticket);

        return ticket;
    }

    public PricingStrategy determinePricingStrategy(double capacityPercentage) {
        if (capacityPercentage < 10) {
            return new PromoPricingStrategy();
        } else if (capacityPercentage > 80) {
            return new PremiumPricingStrategy();
        } else {
            return new StandardPricingStrategy();
        }
    }
}