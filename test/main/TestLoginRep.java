package main;
import com.windowtester.runtime.swing.locator.JButtonLocator;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.locator.LabeledTextLocator;
import com.windowtester.runtime.swing.locator.JListLocator;

public class TestLoginRep extends UITestCaseSwing {

	/**
	* Create an Instance
	 */
	public TestLoginRep() {
		super(main.Login.class);
	}

	/**
	* Main test method.
	*/
	public void testTestLoginRep() throws Exception {
		IUIContext ui = getUI();
		ui.click(new JButtonLocator("Login"));
		ui.click(new JButtonLocator("REP"));
		ui.click(new JButtonLocator("Create"));
		ui.click(new JButtonLocator("Create"));
		ui.click(new JButtonLocator("Create"));
		ui.click(new JButtonLocator("Create"));
		ui.click(new JButtonLocator("Create"));
		ui.click(new JButtonLocator("previous "));
		ui.click(new JButtonLocator("signout"));
		ui.click(new LabeledTextLocator("Username:"));
		ui.enterText("scso");
		ui.click(new LabeledTextLocator("Password:"));
		ui.enterText("password");
		ui.click(new JButtonLocator("Login"));
		ui.click(new JButtonLocator("REP"));
		ui.click(new JListLocator("Id: 0, Client: XYZ corp, createdBy: cso1, Status: CREATED"));
		ui.click(new JButtonLocator("Reject"));
		ui.click(new JListLocator("Id: 1, Client: XYZ corp, createdBy: cso1, Status: CREATED"));
		ui.click(new JButtonLocator("Send to Fm"));
		ui.click(new JListLocator("Id: 2, Client: XYZ corp, createdBy: cso1, Status: CREATED"));
		ui.click(new JButtonLocator("Send to Fm"));
		ui.click(new JListLocator("Id: 3, Client: XYZ corp, createdBy: cso1, Status: CREATED"));
		ui.click(new JButtonLocator("Send to Fm"));
		ui.click(new JListLocator("Id: 4, Client: XYZ corp, createdBy: cso1, Status: CREATED"));
		ui.click(new JButtonLocator("Reject"));
		ui.click(new JListLocator("Id: 4, Client: XYZ corp, createdBy: cso1, Status: REJECTED"));
		ui.click(new JButtonLocator("Delete"));
		ui.click(new JButtonLocator("Create"));
		ui.click(new JButtonLocator("previous "));
		ui.click(new JButtonLocator("signout"));
		ui.click(new LabeledTextLocator("Username:"));
		ui.enterText("fm");
		ui.click(new LabeledTextLocator("Password:"));
		ui.enterText("password");
		ui.click(new JButtonLocator("Login"));
		ui.click(new JButtonLocator("REP"));
		ui.click(new JListLocator("Id: 1, Client: XYZ corp, createdBy: cso1, Status: REVIEWED_BY_SCSO"));
		ui.click(new JButtonLocator("Send to Admin"));
		ui.click(new JListLocator("Id: 2, Client: XYZ corp, createdBy: cso1, Status: REVIEWED_BY_SCSO"));
		ui.click(new JButtonLocator("Send to Admin"));
		ui.click(new JListLocator("Id: 3, Client: XYZ corp, createdBy: cso1, Status: REVIEWED_BY_SCSO"));
		ui.click(new JButtonLocator("Reject"));
		ui.click(new JButtonLocator("previous "));
		ui.click(new JButtonLocator("signout"));
		ui.click(new LabeledTextLocator("Username:"));
		ui.enterText("admin");
		ui.click(new LabeledTextLocator("Password:"));
		ui.enterText("password");
		ui.click(new JButtonLocator("Login"));
		ui.click(new JButtonLocator("REP"));
		ui.click(new JListLocator("Id: 0, Client: XYZ corp, createdBy: cso1, Status: REJECTED"));
		ui.click(new JListLocator("Id: 1, Client: XYZ corp, createdBy: cso1, Status: REVIEWED_BY_FM"));
		ui.click(new JButtonLocator("Approve"));
		ui.click(new JListLocator("Id: 2, Client: XYZ corp, createdBy: cso1, Status: REVIEWED_BY_FM"));
		ui.click(new JButtonLocator("Reject"));
		ui.click(new JListLocator("Id: 5, Client: XYZ corp, createdBy: scso, Status: CREATED"));
		ui.click(new JButtonLocator("previous "));
		ui.click(new JButtonLocator("signout"));
		ui.click(new LabeledTextLocator("Username:"));
		ui.enterText("scso");
		ui.click(new LabeledTextLocator("Password:"));
		ui.enterText("password");
		ui.click(new JButtonLocator("Login"));
		ui.click(new JButtonLocator("REP"));
		ui.click(new JListLocator("Id: 1, Client: XYZ corp, createdBy: cso1, Status: APPROVED"));
		ui.click(new JButtonLocator("Send For Execution"));
		ui.click(new JButtonLocator("Sign out"));
	}

}