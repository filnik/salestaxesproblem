package filnik.salestaxesproblem.test;

import filnik.salestaxesproblem.activities.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import org.w3c.dom.Text;


public class input1 extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public input1() {
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
		solo.waitForActivity(filnik.salestaxesproblem.activities.MainActivity.class, 2000);
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.name), "book");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.price), "12.49");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.add_button));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.name), "music CD");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 3));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.price), "14.99");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.add_button));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.name));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.name), "chocolate bar");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.type));
		solo.clickOnView(solo.getView(android.R.id.text1, 1));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.price));
		solo.enterText((android.widget.EditText) solo.getView(filnik.salestaxesproblem.R.id.price), "0.85");
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.add_button));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.cart));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Pay

		String totalFinalText = "Sales Taxes: 1.50\nTotal: 29.83";

		assertTrue(((TextView) solo.getView(filnik.salestaxesproblem.R.id.total)).getText().toString().equals(totalFinalText));
		solo.clickOnView(solo.getView(filnik.salestaxesproblem.R.id.pay));
	}
}
