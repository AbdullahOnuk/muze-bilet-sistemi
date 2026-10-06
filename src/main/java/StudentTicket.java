import java.util.UUID; // Rastgele benzersiz ID üretmek için

public class StudentTicket implements Ticket {
    private final String ticketId;
    private final String visitorName;
    private final double basePrice;

    public StudentTicket(String visitorName, double basePrice) {
        this.ticketId = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // Rastgele 8 haneli ID
        this.visitorName = visitorName;
        this.basePrice = basePrice;
    }

    @Override
    public String getTicketId() { return ticketId; }

    @Override
    public String getVisitorName() { return visitorName; }

    @Override
    public double calculatePrice() { return basePrice * 0.5; }

    @Override
    public String getVisitorType() { return "Öğrenci"; }
}