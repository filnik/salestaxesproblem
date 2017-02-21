package filnik.salestaxesproblem.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;

import filnik.salestaxesproblem.R;
import filnik.salestaxesproblem.activities.MainActivity;

public class input2 extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;

  	public input2() {
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
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50
		 */
		solo.waitForActivity(MainActivity.class, 2000);
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.name), "box of chocolates");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.price), "10.00");
		solo.clickOnView(solo.getView(R.id.imported));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.add_button));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.name), "bottle of perfume");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.price), "47.50");
		solo.clickOnView(solo.getView(R.id.imported));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.add_button));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.cart));
		solo.waitForDialogToOpen(5000);
		String totalFinalText = String.format(solo.getString(R.string.total), 7.65F, 65.15F);
		assertTrue(((TextView) solo.getView(filnik.salestaxesproblem.R.id.total)).getText().toString().equals(totalFinalText));
		solo.sleep(8000); // remove in production
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.pay));
	}
}
