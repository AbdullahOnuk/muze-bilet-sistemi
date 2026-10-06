// Doluluk < %10 ise %20 İndirim
public class PromoPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.80;
    }

    @Override
    public String getStrategyName() {
        return "Promosyon Stratejisi (%20 İndirim)";
    }
}


