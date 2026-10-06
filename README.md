# Müze Bilet Sistemi

Nesneye Yönelik Yazılım dersi kapsamında ekip olarak geliştirdiğimiz, Java tabanlı bir terminal uygulaması. Projede nesne yönelimli tasarım ve tasarım desenlerini bir bilet satış senaryosu üzerinde uyguladık.

## Özellikler

- Öğrenci ve tam bilet oluşturma
- Doluluk oranına göre dinamik fiyatlandırma
- Sesli rehber ve hızlı geçiş ek hizmetleri
- Nakit ödeme ve para üstü hesaplama
- Bilet kayıtlarını PostgreSQL veritabanına kaydetme
- Kullanıcı girişlerini doğrulama

## Kullanılan tasarım desenleri

| Desen | Projedeki kullanımı |
| --- | --- |
| Factory Method | Öğrenci ve tam bilet nesnelerinin oluşturulması |
| Strategy | Doluluk oranına göre fiyatlandırma yönteminin seçilmesi |
| Decorator | Bilete sesli rehber ve hızlı geçiş eklenmesi |

`TerminalUI` kullanıcı etkileşimini, `TicketService` iş akışını, `PostgresTicketRepository` veritabanına kayıt işlemini yürütür.

## Gereksinimler

- JDK 17 veya üzeri
- Apache Maven 3.6.3 veya üzeri
- PostgreSQL

## Kurulum

1. Projeyi indirin veya klonlayın ve proje klasörünü açın.
2. PostgreSQL üzerinde `muze_db` adlı veritabanını oluşturun.
3. Bu veritabanında [database/schema.sql](database/schema.sql) dosyasını çalıştırın.
4. Bağlantı bilgilerini ortam değişkenleriyle tanımlayın. Windows PowerShell örneği:

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/muze_db"
$env:DB_USER = "postgres"
$env:DB_PASSWORD = "kendi-veritabani-sifreniz"
```

5. Testleri çalıştırın ve uygulamayı başlatın:

```sh
mvn test
mvn compile exec:java
```

IntelliJ IDEA ile açarken `pom.xml` dosyasını Maven projesi olarak içe aktarabilirsiniz. IDE üzerinden çalıştırırsanız ortam değişkenlerini çalıştırma yapılandırmasına ekleyin.

## Örnek kullanım

Ana menüden öğrenci veya tam bilet seçin. Ziyaretçi adı, taban fiyat ve doluluk oranını girin. İsteğe bağlı ek hizmetleri seçip nakit ödeme tutarını girerek işlemi tamamlayın. Ondalıklı tutarlarda nokta kullanın.

## Projenin kapsamı

Bu bir öğrenci ders projesidir. GitHub için Maven yapısı, kurulum açıklaması ve örnek veritabanı şeması eklenmiş; veritabanı şifresi ortam değişkenine taşınmıştır. Özgün bilet satış akışı korunmuştur.

Mevcut sürümde veritabanına temel bilet, ek hizmetler ve ödeme tamamlanmadan önce kaydedilir. Ek hizmetlerle oluşan nihai tutar veritabanında güncellenmez. Veritabanı hatası terminale yazılır ve uygulama bilet akışına devam eder.

## Testler

JUnit testleri öğrenci biletindeki %50 indirimi ve tam biletin taban fiyatını doğrular.
