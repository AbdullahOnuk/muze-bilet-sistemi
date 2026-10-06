public class Main {
    public static void main(String[] args) {

        // Veritabanı bağlantı nesnesini oluştur
        PostgresTicketRepository repository = new PostgresTicketRepository();

        //  Servisi oluştur ve veritabanını içine enjekte et
        TicketService service = new TicketService(repository);

        // Kullanıcı arayüzünü oluştur ve servisi içine enjekte et
        TerminalUI ui = new TerminalUI(service);

        //  Sistemi başlat
        ui.start();
    }
}