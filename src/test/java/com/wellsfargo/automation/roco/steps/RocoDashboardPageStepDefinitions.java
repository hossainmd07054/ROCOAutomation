package com.wellsfargo.automation.roco.steps;

import org.testng.Reporter;
import org.testng.annotations.Test;
import com.wellsfargo.automation.roco.base.RocoTestBase;
import com.wellsfargo.automation.roco.pages.RocoDashboardPageObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RocoDashboardPageStepDefinitions extends RocoTestBase{

	RocoTestBase rocoTestBase = new RocoTestBase();
	RocoDashboardPageObjects rocoDashboardPageObjects = new RocoDashboardPageObjects();
	@Test
	@Given("user is on ROCO application")
	public void user_is_on_roco_application() throws Exception {
		rocoTestBase.openURL();
//		rocoDashboardPageObjects.getUsernameTextBox.sendKeys("Admin");
//		rocoDashboardPageObjects.getPasswordTextBox.sendKeys("admin123");
//		rocoDashboardPageObjects.getLoginButton.click();
		Reporter.log("Pass");
	}
	@When("user verify sign on page with {string} and {string}")
	public void user_verify_sign_on_page_with_and(String string, String string2) {
		rocoDashboardPageObjects.getUsernameTextBox.sendKeys("Admin");
		rocoDashboardPageObjects.getPasswordTextBox.sendKeys("admin123");
		rocoDashboardPageObjects.getLoginButton.click();
		Reporter.log("");		
	}
	@Then("user verifies Channel Secure Sign On label is displayed in Channel Secure Sign On Page")
	public void user_verifies_channel_secure_sign_on_label_is_displayed_in_channel_secure_sign_on_page() {

	}
}
