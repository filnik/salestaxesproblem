package filnik.salestaxesproblem.model.items;

/**
 * Created by fil on 19/02/17.
 */

public class BookItem extends Item {

    public BookItem(String name, boolean imported, double price) {
        super(name, imported, price);
    }

    @Override
    protected int baseTax() {
        return 0;
    }
}
