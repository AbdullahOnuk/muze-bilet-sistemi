// Doluluk > %80 ise %20 Zam
public class PremiumPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 1.20;
    }

    @Override
    public String getStrategyName() {
        return "Premium Strateji (Yoğunluk Zammı)";
    }
}