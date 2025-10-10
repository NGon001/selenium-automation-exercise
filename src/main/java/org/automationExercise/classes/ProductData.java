package org.automationExercise.classes;

public class ProductData {
    private final String name;
    private final double price;

    public ProductData(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public String toString() {
        return "ProductData{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static double textPriceToFloat(String textPrice) {
        if (textPrice == null) return 0.0;

        // Extract only digits and optional decimal parts
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("[0-9]+(?:\\.[0-9]+)?")
                .matcher(textPrice.replace(",", ""));

        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }
}
