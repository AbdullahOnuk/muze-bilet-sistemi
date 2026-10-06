import java.util.Scanner;

public class TerminalUI {
    private final TicketService ticketService;
    private final Scanner scanner;

    public TerminalUI(TicketService ticketService) {
        this.ticketService = ticketService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;
        System.out.println("=========================================");
        System.out.println("🏛️ MÜZE BİLET SİSTEMİNE HOŞ GELDİNİZ 🏛️");
        System.out.println("=========================================");

        while (isRunning) {
            System.out.println("\n--- ANA MENÜ ---");
            System.out.println("1. Yeni Öğrenci Bileti Kes");
            System.out.println("2. Yeni Tam Bilet Kes");
            System.out.println("0. Sistemden Çıkış Yap");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    kesBiletSureci(new StudentTicketFactory(), "Öğrenci");
                    break;
                case "2":
                    kesBiletSureci(new FullTicketFactory(), "Tam");
                    break;
                case "0":
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("❌ Hatalı seçim yaptınız, lütfen tekrar deneyin.");
            }
        }
        scanner.close();
    }

    private void kesBiletSureci(TicketFactory factory, String ticketTypeName) {
        System.out.println("\n--- " + ticketTypeName.toUpperCase() + " BİLETİ OLUŞTURMA ---");

        // 1. ADIM: İSİM ALMA
        String visitorName = "";
        boolean isNameValid = false;
        while (!isNameValid) {
            System.out.print("Ziyaretçi Adı ve Soyadı: ");
            visitorName = scanner.nextLine().trim();

            if (visitorName.isEmpty()) {
                System.out.println("⚠️ Uyarı: İsim alanı boş bırakılamaz!");
            }
            else if (!visitorName.matches("^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$")) {
                System.out.println("⚠️ Uyarı: İsim alanı rakam veya özel karakter içeremez, lütfen sadece harf kullanın!");
            }
            else {
                isNameValid = true;
            }
        }

        // 2. ADIM: TABAN FİYAT ALMA
        double basePrice = 0;
        boolean isPriceValid = false;
        while (!isPriceValid) {
            System.out.print("Müzenin güncel taban fiyatını giriniz: ");
            try {
                basePrice = Double.parseDouble(scanner.nextLine());
                if (basePrice <= 0) {
                    System.out.println("⚠️ Uyarı: Fiyat 0'dan büyük olmalıdır!");
                } else {
                    isPriceValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Hata: Lütfen fiyatı sadece rakamlarla giriniz!");
            }
        }

        // =========================================================
        // 3. ADIM: STRATEGY PATTERN - KATI SINIRLI DOLULUK ORANI ALMA
        // =========================================================
        double capacityPercentage = 0;
        boolean isCapacityValid = false;
        while (!isCapacityValid) {
            System.out.print("Seansın şu anki doluluk oranını giriniz (%): ");
            try {
                capacityPercentage = Double.parseDouble(scanner.nextLine());

                // 0 ile 100 aralığı dışında bir değer girilirse hata verip tekrar soracak
                if (capacityPercentage < 0 || capacityPercentage > 100) {
                    System.out.println("❌ KORUMA HATASI: Doluluk oranı %0 ile %100 arasında olmak zorundadır!");
                } else {
                    isCapacityValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Hata: Lütfen doluluk oranını sadece rakamlarla giriniz! (Örn: 45 veya 80)");
            }
        }

        // Uygun fiyatlandırma stratejisini seç ve dinamik taban fiyatı belirle
        PricingStrategy strategy = ticketService.determinePricingStrategy(capacityPercentage);
        System.out.println("Uygulanan Fiyatlandırma: " + strategy.getStrategyName());
        double dynamicBasePrice = strategy.calculatePrice(basePrice);

        // 4. ADIM: TEMEL BİLETİ ÜRET (Factory Pattern)
        Ticket myTicket = ticketService.createAndSaveTicket(factory, visitorName, dynamicBasePrice);

        // =========================================================
        // 5. ADIM: DECORATOR PATTERN - TEK SEFERLİK EK HİZMET KORUMASI
        // =========================================================
        boolean isAddingExtras = true;

        // Hizmetlerin daha önce eklenip eklenmediğini tutan kontrol bayrakları
        boolean hasAudioGuide = false;
        boolean hasFastTrack = false;

        while (isAddingExtras) {
            System.out.println("\n--- EK HİZMETLER ---");
            System.out.println("Şu anki tutar: " + myTicket.calculatePrice() + " TL");
            System.out.println("1. Sesli Rehber Ekle (+50 TL) " + (hasAudioGuide ? "[EKLENDİ]" : ""));
            System.out.println("2. Hızlı Geçiş Ekle (+100 TL) " + (hasFastTrack ? "[EKLENDİ]" : ""));
            System.out.println("0. Ek Hizmet İstemiyorum / Devam Et");
            System.out.print("Seçiminiz: ");

            String extraChoice = scanner.nextLine();

            switch (extraChoice) {
                case "1":
                    if (hasAudioGuide) {
                        System.out.println("⚠️ KORUMA UYARISI: Sesli Rehber bu bilete zaten eklendi! Tekrar ekleyerek bilet fiyatını şişiremezsiniz.");
                    } else {
                        myTicket = new AudioGuideDecorator(myTicket); // Sarmala
                        hasAudioGuide = true; // Bayrağı kaldır, bir daha eklenemesin
                        System.out.println("✅ Sesli rehber başarıyla bilete eklendi.");
                    }
                    break;
                case "2":
                    if (hasFastTrack) {
                        System.out.println("⚠️ KORUMA UYARISI: Hızlı Geçiş bu bilete zaten eklendi! Tekrar ekleyerek bilet fiyatını şişiremezsiniz.");
                    } else {
                        myTicket = new FastTrackDecorator(myTicket); // Sarmala
                        hasFastTrack = true; // Bayrağı kaldır, bir daha eklenemesin
                        System.out.println("✅ Hızlı geçiş başarıyla bilete eklendi.");
                    }
                    break;
                case "0":
                    isAddingExtras = false;
                    break;
                default:
                    System.out.println("❌ Hatalı seçim yaptınız, lütfen listedeki menü elemanlarını kullanın.");
            }
        }

        // Nihai fiyatı al
        double finalPrice = myTicket.calculatePrice();

        System.out.println("\n✅ Bilet İşlemi Tamamlandı!");
        System.out.println("Bilet ID: " + myTicket.getTicketId());
        System.out.println("Ziyaretçi: " + myTicket.getVisitorName());
        System.out.println("Bilet Türü: " + myTicket.getVisitorType());
        System.out.println("Ödenmesi Gereken Nihai Tutar: " + finalPrice + " TL");

        // =========================================================
        // 6. ADIM: ÖDEME ALMA VE PARA ÜSTÜ HESAPLAMA
        // =========================================================
        boolean paymentComplete = false;
        while (!paymentComplete) {
            System.out.print("\nMüşteriden alınan nakit tutarını giriniz: ");
            try {
                double givenAmount = Double.parseDouble(scanner.nextLine());

                if (givenAmount < finalPrice) {
                    double eksikMiktar = finalPrice - givenAmount;
                    System.out.println("⚠️ Uyarı: Eksik ödeme! " + eksikMiktar + " TL daha ödenmesi gerekiyor.");
                } else {
                    double paraUstu = givenAmount - finalPrice;
                    System.out.println("✅ Ödeme Başarılı!");
                    if (paraUstu > 0) {
                        System.out.println("💵 Verilecek Para Üstü: " + paraUstu + " TL");
                    }
                    paymentComplete = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Hata: Lütfen nakit tutarını sadece rakamlarla giriniz!");
            }
        }
        System.out.println("-----------------------------------------");
    }
}