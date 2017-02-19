package filnik.salestaxesproblem.model.items;

/**
 * Created by fil on 19/02/17.
 */

public class Item {
    private String name = "";
    private boolean imported = false;
    private double price = 0;

    protected int baseTax(){
        return 10;
    }

    private int importedTax(){
        return 5;
    }

    private double roundTo005(double x){
        return Math.round(x * 20.0) / 20.0;
    }

    public double getTotalTax(){
        double baseTaxCalculated = baseTax() * price / 100;
        double importedTaxCalculated = importedTax() * price / 100;
        return imported ? baseTaxCalculated + importedTaxCalculated : baseTaxCalculated;
    }

    public double roundedTotalTax(){
        return roundTo005(getTotalTax());
    }

    public double getPrice(){
        return price;
    }

    public double getPriceWithTax(){
        return price + roundedTotalTax();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item){
            Item otherItem = (Item) obj;
            return name.equals(otherItem.name) && price == otherItem.getPrice() && imported == otherItem.imported;
        }
        return false;
    }
}