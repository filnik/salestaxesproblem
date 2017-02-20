package filnik.salestaxesproblem.fragments;

import android.app.DialogFragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.activities.CartActivity;
import filnik.salestaxesproblem.model.Basket;
import filnik.salestaxesproblem.model.CartAdapter;
import filnik.salestaxesproblem.model.items.Item;

/**
 * Created by fil on 20/02/17.
 */
public class CartFragment extends DialogFragment {
    @BindView(R.id.close)       Button closeButton;
    @BindView(R.id.pay)         Button payButton;
    @BindView(R.id.cart_list)   RecyclerView cartView;
    private CartAdapter cartAdapter;
    private Basket basket;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, null, false);
        ButterKnife.bind(this, root);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof CartActivity){
                    ((CartActivity) getActivity()).onCartClose();
                }
                dismiss();
            }
        });

        cartAdapter = new CartAdapter(basket);
        cartView.setAdapter(cartAdapter);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), R.string.paid, Toast.LENGTH_SHORT).show();
                cartAdapter.clear();
                // do awesome things with paypal
            }
        });

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        getDialog().setTitle(getString(R.string.cart));

        root.setMinimumWidth((int) (displayRectangle.width() * 0.7));
        root.setMinimumHeight((int) (displayRectangle.height() * 0.7));

        return root;
    }

    public void setBasket(Basket basket){
        this.basket = basket;
    }
}