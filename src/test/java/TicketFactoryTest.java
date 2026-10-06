// Dosya: TicketFactoryTest.java (GÜNCELLENDİ)

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class    TicketFactoryTest {

    @Test
    public void testStudentTicketFactory() {
        TicketFactory factory = new StudentTicketFactory();
        Ticket ticket = factory.createTicket("Test Öğrenci", 100.0);

        assertEquals("Öğrenci", ticket.getVisitorType());
        assertEquals(50.0, ticket.calculatePrice(), 0.001);
    }

    @Test
    public void testFullTicketFactory() {
        TicketFactory factory = new FullTicketFactory();
        Ticket ticket = factory.createTicket("Test Tam", 100.0);

        assertEquals("Tam", ticket.getVisitorType());
        assertEquals(100.0, ticket.calculatePrice(), 0.001);
    }
}
