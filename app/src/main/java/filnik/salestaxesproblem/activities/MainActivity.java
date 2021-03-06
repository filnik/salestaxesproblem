package filnik.salestaxesproblem.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.fragments.CartFragment;
import filnik.salestaxesproblem.model.Basket;
import filnik.salestaxesproblem.model.items.BookItem;
import filnik.salestaxesproblem.model.items.FoodItem;
import filnik.salestaxesproblem.model.items.Item;
import filnik.salestaxesproblem.model.items.MedicalItem;

public class MainActivity extends Activity implements CartActivity {
    /*
    Bisogna applicare una tassa base a un tasso del 10% per tutti i beni eccetto libri, cibo e prodotti medici
    esclusi. Bisogna inoltre aggiungere una tassa d'importo per tutti i beni importati al tasso del
    5%, senza eccezioni.

    Quando compro degli oggetti ricevo una ricevuta che elenca il nome di tutti gli oggetti e il loro
    prezzo (tasse incluse), finendo con il costo totale degli oggetti, e l'ammontare totale delle tasse
    che ho pagato.

    La regola per arrotondare le tasse è che un tasso dell'n%, un prezzo base di p contiene un ammontare
    di tasse pari a np/100 arrotondato allo 0.05 più vicino.

    Scrivi un'applicazione che stampa i dettagli delle ricevute per queste liste della spesa...
    */

    private CartFragment cartFragment;
    protected Validator validator = new Validator(this);

    @BindView(R.id.name)     EditText nameItem;
    @BindView(R.id.price)    EditText priceItem;
    @BindView(R.id.type)     Spinner type;
    @BindView(R.id.imported) CheckBox imported;
    private MenuItem cartItem;
    private int itemsAdded = 0;
    private Basket basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupValidator();
        basket = new Basket();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        cartItem = menu.findItem(R.id.cart);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) {
            openCart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_button)
    public void onAddButton(){
        validator.validate();
    }

    private void setupValidator(){
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                itemsAdded++;
                double price = Double.parseDouble(priceItem.getText().toString().replace("[^0-9\\.]", ""));

                Item item;
                String[] options = getResources().getStringArray(R.array.item_types);
                List<String> list = Arrays.asList(options);

                switch (list.indexOf(type.getSelectedItem().toString())){
                    case 0:
                        item = new BookItem(nameItem.getText().toString(), imported.isChecked(), price);
                        break;
                    case 1:
                        item = new FoodItem(nameItem.getText().toString(), imported.isChecked(), price);
                        break;
                    case 2:
                        item = new MedicalItem(nameItem.getText().toString(), imported.isChecked(), price);
                        break;
                    default:
                        item = new Item(nameItem.getText().toString(), imported.isChecked(), price);
                        break;
                }
                basket.add(item);
                ActionItemBadge.update(MainActivity.this, cartItem,
                        getResources().getDrawable(R.mipmap.ic_shopping_cart_white),
                        ActionItemBadge.BadgeStyles.RED, itemsAdded);
                clearScreen();
            }

            private void clearScreen(){
                nameItem.setText("");
                priceItem.setText("");
                type.setSelection(0);
                imported.setChecked(false);
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors){
                    for (Rule rule : error.getFailedRules()){
                        Toast.makeText(MainActivity.this, rule.getMessage(MainActivity.this), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        validator.put(nameItem, new QuickRule<EditText>() {
            @Override
            public boolean isValid(EditText editText) {
                return !editText.getText().toString().equals("") && !editText.getText().toString().matches("\\W");
            }

            @Override
            public String getMessage(Context context) {
                return getString(R.string.name_empty);
            }
        });

        validator.put(priceItem, new QuickRule<EditText>() {
            @Override
            public boolean isValid(EditText editText) {
                try{
                    Double.parseDouble(priceItem.getText().toString().replace("[^0-9\\.]", ""));
                } catch (final NumberFormatException e) {
                    return false;
                }
                return !editText.getText().toString().equals("") && !editText.getText().toString().matches("\\W");
            }

            @Override
            public String getMessage(Context context) {
                return getString(R.string.price_invalid);
            }
        });
    }

    private void openCart(){
        if (cartFragment != null){
            return;
        }
        if (basket.size() == 0){
            Toast.makeText(this, getString(R.string.no_items), Toast.LENGTH_SHORT).show();
            return;
        }
        cartFragment = new CartFragment();
        cartFragment.setBasket(basket);
        cartFragment.setCancelable(false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        cartFragment.show(ft, getString(R.string.cart));
    }

    @Override
    public void onCartClose() {
        cartFragment = null;
    }

    @Override
    public void onPaid() {
        cartFragment = null;
        cartItem.getActionView().findViewById(R.id.menu_badge).setVisibility(View.GONE);
        itemsAdded = 0;
    }

    @Override
    public void onClearCart() {
        onPaid();
    }
}