package filnik.salestaxesproblem.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.QuickRule;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.fragments.CartFragment;
import filnik.salestaxesproblem.model.items.Item;

public class MainActivity extends AppCompatActivity implements CartActivity {

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

    --------

    Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
    products that are exempt. Import duty is an additional sales tax applicable on all imported goods
    at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid.
The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...

INPUT:

Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

OUTPUT

Output 1:
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
     */
    private CartFragment cartFragment;
    private EventBus bus = EventBus.getDefault();

    protected Validator validator = new Validator(this);

    @BindView(R.id.name) EditText nameItem;
    @BindView(R.id.price) EditText priceItem;
    @BindView(R.id.imported) CheckBox imported;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupValidator();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart_list) {
            openCart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_button)
    private void onAddButton(){
        validator.validate();
    }

    private void setupValidator(){
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                double price = Double.parseDouble(priceItem.getText().toString());
                bus.post(new Item(nameItem.getText().toString(), imported.isChecked(), price));
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
                    Double.parseDouble(priceItem.getText().toString());
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
        cartFragment = new CartFragment();
        cartFragment.setCancelable(false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        cartFragment.show(ft, getString(R.string.cart));
    }

    @Override
    public void onCartClose() {
        cartFragment = null;
    }
}
