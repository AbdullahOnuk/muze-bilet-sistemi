import java.util.UUID;

public class FullTicket implements Ticket {
    private final String ticketId;
    private final String visitorName;
    private final double basePrice;

    // Constructor
    public FullTicket(String visitorName, double basePrice) {
        // 8 haneli rastgele ve benzersiz bir Bilet ID'si üretir
        this.ticketId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.visitorName = visitorName;
        this.basePrice = basePrice;
    }

    @Override
    public String getTicketId() {
        return ticketId;
    }

    @Override
    public String getVisitorName() {
        return visitorName;
    }

    @Override
    public double calculatePrice() {
        return basePrice; // Tam bilet olduğu için indirim yok, taban fiyatı direkt döndür
    }

    @Override
    public String getVisitorType() {
        return "Tam";
    }
}