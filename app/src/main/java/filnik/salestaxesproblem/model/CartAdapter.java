package filnik.salestaxesproblem.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.model.items.Item;

/**
 * Created by fil on 20/02/17.
 */

class CartHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text) TextView text;

    CartHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}

public class CartAdapter extends RecyclerView.Adapter {
    private Basket basket;

    public CartAdapter(Basket basket) {
        this.basket = basket;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.element_cart, null, false);
        return new CartHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartHolder myHolder = (CartHolder) holder;
        Item item = basket.getItem(position);
        Context context = holder.itemView.getContext();
        String imported = item.isImported() ? " " + context.getString(R.string.imported).toLowerCase() + " " : " ";

        myHolder.text.setText("" + (position + 1) + imported + " " + item.getName() +
                context.getString(R.string.at) + " " + item.getPriceWithTax().toString());
    }

    @Override
    public int getItemCount() {
        return basket.size();
    }

    public void clear(){
        basket.clear();
        notifyDataSetChanged();
    }
}