public class AudioGuideDecorator extends TicketDecorator {

    public AudioGuideDecorator(Ticket decoratedTicket) {
        super(decoratedTicket);
    }

    @Override
    public double calculatePrice() {
        // Asıl biletin fiyatını al, üzerine 50 TL rehber ücreti ekle
        return super.calculatePrice() + 50.0;
    }

    @Override
    public String getVisitorType() {
        // Asıl biletin türünün yanına ek hizmeti yaz
        return super.getVisitorType() + " (+Sesli Rehber)";
    }
}
