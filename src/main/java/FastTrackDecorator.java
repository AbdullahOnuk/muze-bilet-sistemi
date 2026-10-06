public class FastTrackDecorator extends TicketDecorator {

    public FastTrackDecorator(Ticket decoratedTicket) {
        super(decoratedTicket);
    }

    @Override
    public double calculatePrice() {
        // Asıl biletin fiyatını al, üzerine 100 TL hızlı geçiş ücreti ekle
        return super.calculatePrice() + 100.0;
    }

    @Override
    public String getVisitorType() {
        return super.getVisitorType() + " (+Hızlı Geçiş)";
    }
}