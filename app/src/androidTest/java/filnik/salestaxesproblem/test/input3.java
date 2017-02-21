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
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
		 */
		solo.waitForActivity(MainActivity.class, 2000);
		solo.clickOnView(solo.getView(R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(R.id.name), "bottle of perfume");
		solo.clickOnView(solo.getView(R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		solo.clickOnView(solo.getView(R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "27.99");
		solo.clickOnView(solo.getView(R.id.imported));
		solo.clickOnView(solo.getView(R.id.add_button));
		solo.clickOnView(solo.getView(R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(R.id.name), "bottle of perfume");
		solo.clickOnView(solo.getView(R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		solo.clickOnView(solo.getView(R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "18.99");
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
		solo.enterText((android.widget.EditText) solo.getView(R.id.price), "11.25");
		solo.clickOnView(solo.getView(R.id.imported));
		solo.clickOnView(solo.getView(R.id.add_button));
		solo.clickOnView(solo.getView(R.id.cart));
		solo.waitForDialogToOpen(5000);
		String totalFinalText = String.format(solo.getString(R.string.total), 6.70F, 74.68F);
		assertTrue(((TextView) solo.getView(R.id.total)).getText().toString().equals(totalFinalText));
		solo.sleep(8000);
		solo.clickOnView(solo.getView(R.id.pay));
	}
}
