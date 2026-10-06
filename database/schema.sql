-- Önce muze_db veritabanını oluşturun, ardından bu dosyayı içinde çalıştırın.
CREATE TABLE IF NOT EXISTS tickets (
    ticket_id BIGSERIAL PRIMARY KEY,
    visitor_name TEXT NOT NULL,
    visitor_type TEXT NOT NULL,
    calculated_price NUMERIC(12, 2) NOT NULL
);
