package filnik.salestaxesproblem.model.items;

/**
 * Created by fil on 19/02/17.
 */

public class BookItem extends Item {

    @Override
    protected int baseTax() {
        return 0;
    }
}
