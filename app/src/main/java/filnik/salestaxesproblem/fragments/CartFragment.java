package filnik.salestaxesproblem.fragments;

import android.app.DialogFragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.activities.CartActivity;
import filnik.salestaxesproblem.model.Basket;
import filnik.salestaxesproblem.model.CartAdapter;
import filnik.salestaxesproblem.view.DividerItemDecoration;

/**
 * Created by fil on 20/02/17.
 */
public class CartFragment extends DialogFragment {
    @BindView(R.id.close)       Button closeButton;
    @BindView(R.id.clear)       Button clearButton;
    @BindView(R.id.pay)         Button payButton;
    @BindView(R.id.total)       TextView total;
    @BindView(R.id.cart_list)   RecyclerView cartView;
    private CartAdapter cartAdapter;
    private Basket basket;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, null, false);
        ButterKnife.bind(this, root);

        Locale.setDefault(Locale.US);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof CartActivity){
                    ((CartActivity) getActivity()).onCartClose();
                }
                dismiss();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), R.string.cart_cleared, Toast.LENGTH_SHORT).show();
                cartAdapter.clear();
                if (getActivity() instanceof CartActivity){
                    ((CartActivity) getActivity()).onClearCart();
                }
                dismiss();
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do awesome things with paypal
                Toast.makeText(getActivity(), R.string.paid, Toast.LENGTH_SHORT).show();
                cartAdapter.clear();
                if (getActivity() instanceof CartActivity){
                    ((CartActivity) getActivity()).onPaid();
                }
                dismiss();
            }
        });

        cartAdapter = new CartAdapter(basket);

        cartView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        cartView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartView.setAdapter(cartAdapter);
        cartView.invalidate();

        total.setText(String.format(getString(R.string.total), basket.totalTaxes(), basket.totalPrice()));
        Log.d("TOTAL", String.format(getString(R.string.total), basket.totalTaxes(), basket.totalPrice()));

        getDialog().setTitle(getString(R.string.cart));

        return root;
    }

    public void setBasket(Basket basket){
        this.basket = basket;
    }
}