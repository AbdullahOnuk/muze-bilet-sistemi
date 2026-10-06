// Doluluk %10 - %80 arası
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }

    @Override
    public String getStrategyName() {
        return "Standart Strateji (Normal Fiyat)";
    }
}
