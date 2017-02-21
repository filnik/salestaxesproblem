package filnik.salestaxesproblem.model.items;

import android.util.Log;

import filnik.salestaxesproblem.model.Util;

/**
 * Created by fil on 19/02/17.
 */

public class Item {
    private String name = "";
    private boolean imported = false;
    private double price = 0;

    public Item(String name, boolean imported, double price) {
        this.name = name;
        this.imported = imported;
        this.price = price;
    }

    protected int baseTax(){
        return 10;
    }

    private int importedTax(){
        return 5;
    }

    public double getTotalTax(){
        double taxPercent = imported ? baseTax() + importedTax() : baseTax();
        Log.d("BEFORE", "" + (taxPercent * price / 100));
        return Util.roundTo005(taxPercent * price / 100);
    }

    public double getPrice(){
        return price;
    }

    public Double getPriceWithTax(){
        return price + getTotalTax();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item){
            Item otherItem = (Item) obj;
            return name.equals(otherItem.name) && price == otherItem.getPrice() && imported == otherItem.imported;
        }
        return false;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public String getName() {
        return name;
    }
}