package filnik.salestaxesproblem.model;

import java.util.ArrayList;

import filnik.salestaxesproblem.model.items.Item;

/**
 * Created by fil on 19/02/17.
 */

public class Basket {
    private ArrayList<Item> items;
    private double totalPrice = 0;
    private double totalTaxes = 0;

    public Basket() {
        items = new ArrayList<>();
    }

    public Basket(ArrayList<Item> items) {
        this.items = items;
    }

    public void add(Item item){
        totalPrice = totalPrice + item.getPriceWithTax();
        totalTaxes = totalTaxes + item.getTotalTax();
        items.add(item);
    }

    public double totalPrice(){
        return totalPrice;
    }

    public double totalTaxes(){
        return Util.roundTo005(totalTaxes);
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
        totalTaxes = 0;
    }

    public int size() {
        return items.size();
    }

    public Item getItem(int i) {
        return items.get(i);
    }
}
