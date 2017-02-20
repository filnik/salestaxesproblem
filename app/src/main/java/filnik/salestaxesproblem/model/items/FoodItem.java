package filnik.salestaxesproblem.model.items;

/**
 * Created by fil on 19/02/17.
 */

public class FoodItem extends Item {

    public FoodItem(String name, boolean imported, double price) {
        super(name, imported, price);
    }

    @Override
    protected int baseTax() {
        return 0;
    }
}
