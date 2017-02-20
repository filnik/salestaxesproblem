package filnik.salestaxesproblem.model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import filnik.salestaxesproblem.model.items.Item;

/**
 * Created by fil on 20/02/17.
 */

public class CartAdapter extends BaseAdapter {
    private Basket basket = new Basket();

    @Override
    public int getCount() {
        return basket.size();
    }

    @Override
    public Item getItem(int i) {
        return basket.getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public void clear(){
        basket.clear();
        notifyDataSetChanged();
    }
}
