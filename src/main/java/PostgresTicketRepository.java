import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresTicketRepository {

    private static final String URL = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/muze_db");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "");

    public void saveTicket(Ticket ticket) {
        // 1. DEĞİŞİKLİK: ticket_id ve ona karşılık gelen soru işareti silindi.
        String sql = "INSERT INTO tickets (visitor_name, visitor_type, calculated_price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 2. DEĞİŞİKLİK: ticket_id ataması silindi ve sıra numaraları 1, 2, 3 olarak güncellendi.
            pstmt.setString(1, ticket.getVisitorName());
            pstmt.setString(2, ticket.getVisitorType());
            pstmt.setDouble(3, ticket.calculatePrice());

            pstmt.executeUpdate();
            System.out.println(" Veritabanı Bilgisi: Bilet başarıyla kaydedildi! (Ziyaretçi: " + ticket.getVisitorName() + ")");

        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantı veya kayıt hatası: " + e.getMessage());
        }
    }
}
