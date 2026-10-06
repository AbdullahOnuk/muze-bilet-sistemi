// Dosya: TicketDecorator.java
public abstract class TicketDecorator implements Ticket {
    protected Ticket decoratedTicket;

    public TicketDecorator(Ticket decoratedTicket) {
        this.decoratedTicket = decoratedTicket;
    }

    @Override
    public String getTicketId() {
        return decoratedTicket.getTicketId();
    }

    @Override
    public String getVisitorName() {
        return decoratedTicket.getVisitorName();
    }

    @Override
    public double calculatePrice() {
        return decoratedTicket.calculatePrice();
    }

    @Override
    public String getVisitorType() {
        return decoratedTicket.getVisitorType();
    }
}