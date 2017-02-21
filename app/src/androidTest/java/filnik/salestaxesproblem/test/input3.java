package filnik.salestaxesproblem.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.activities.MainActivity;


public class input3 extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public input3() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}

   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}

	public void testRun() {
		/*
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
		 */
		solo.waitForActivity(MainActivity.class, 2000);
		solo.clickOnView(solo.getView(R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(R.id.name), "bottle of perfume");
		solo.clickOnView(solo.getView(R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		solo.clickOnView(solo.getView(R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "32.19");
		solo.clickOnView(solo.getView(R.id.imported));
		solo.clickOnView(solo.getView(R.id.add_button));
		solo.clickOnView(solo.getView(R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(R.id.name), "bottle of perfume");
		solo.clickOnView(solo.getView(R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		solo.clickOnView(solo.getView(R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "20.89");
		solo.clickOnView(solo.getView(R.id.add_button));
		solo.clickOnView(solo.getView(R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(R.id.name), "packet of headache pills");
		solo.clickOnView(solo.getView(R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 2));
		solo.clickOnView(solo.getView(R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "9.75");
		solo.clickOnView(solo.getView(R.id.add_button));
		solo.clickOnView(solo.getView(R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(R.id.name), "box of chocolates");
		solo.clickOnView(solo.getView(R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
		solo.clickOnView(solo.getView(R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "11.85");
		solo.clickOnView(solo.getView(R.id.imported));
		solo.clickOnView(solo.getView(R.id.add_button));
		solo.clickOnView(solo.getView(R.id.cart));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Pay
		String totalFinalText = "Sales Taxes: 6.70\nTotal: 74.68";
		assertTrue(((TextView) solo.getView(R.id.total)).getText().toString().equals(totalFinalText));
		solo.clickOnView(solo.getView(R.id.pay));
	}
}
