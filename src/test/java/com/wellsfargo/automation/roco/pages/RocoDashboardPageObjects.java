package com.wellsfargo.automation.roco.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import static org.testng.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import com.wellsfargo.automation.roco.base.RocoTestBase;

public class RocoDashboardPageObjects extends RocoTestBase{
	public RocoDashboardPageObjects() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.XPATH,using="//*[@name='username']")
	public WebElement getUsernameTextBox;

	@FindBy(how=How.XPATH, using ="//*[@name='password']")
	public WebElement getPasswordTextBox;

	@FindBy(how=How.XPATH, using ="//*[@type='submit']")
	public WebElement getLoginButton;



//	@FindBy (xpath="//*[@name='password']")
//	private WebElement txtbox_password;
//	public WebElement getPasswordTextBox() {
//		return txtbox_password;
//	}
	@FindBy(how=How.XPATH, using="//*[@name='username']")
	public WebElement getUsernameTextBox;

	@FindBy (locator ="xpath=//input[@name=' PASSWORD' ]")
	private WebElement txtbox_password;
	public WebElement getPasswordTextBox() {
		return txtbox_password;
	}
	@FindBy(xpath="//*[@type='submit']")
	private WebElement btn_login;
	public WebElement getLoginButton() {
		return btn_login;
	}
	@FindBy (locator ="xpath=//a[Qid='10gOut]")
	private WebElement btn_signout;
	public WebElement getSignOutButton() {
		return btn_signout;
	}
//	@FindBy (locator ="xpath=//h2[text()='Successfully signed off']")
	@FindBy (xpath="//h2[text()='Successfully signed off']")
	private WebElement get_First_Selected_Option;
	public WebElement getFirstSelectedOption() {
		return get_First_Selected_Option;
	}
	@FindBy (xpath="//div[@id='content']/div/h2")
	private WebElement label_WelcomeSignOnToViewYourAccounts;
	public WebElement getWelcomeSignOnToViewYourAccounts(){
		return label_WelcomeSignOnToViewYourAccounts;
	}
	@FindBy (xpath="//*[@id='tblORSQueue']/tbody")
	private WebElement tableBody_PendingRecords;
	public WebElement getFirstRowofTableOnSelectATeamMemberPopUpInCreateEditCorrespondentBankListPage() {
		return tableBody_PendingRecords;
	}
	public void verifyDefaultDropDownValue(WebElement element, String expectedDefaultValue) {
		try {
			Thread.sleep(1000);
			Select select = new Select(element);
			String actualDefaultValue =	select.getFirstSelectedOption().getText();
			assertEquals(actualDefaultValue.equals(expectedDefaultValue),"expectedDefaultValue is NOT present", "expectedDefaultValue is present");
		} 
		catch (Exception e) {
			e.getMessage();
		}
	}

	public void verifyactialWebElementText(WebElement element, String expestedbinkOrText) {
		String astuathiakOntext = element.getText();
		//		Assert.assertTrue(astuathiakOntext.contains(expestedbinkOrText),astuathiakOntext+"Text Does Not Match"+astuathiakOntext+"Text Matches");
		assertEquals(astuathiakOntext.contains(expestedbinkOrText),astuathiakOntext+"Text Does Not Match"+astuathiakOntext+"Text Matches");
	}

	public void selectPrepareroffiserNanetoAdnioPage(String teamMemberLastName,	String teamMemberFirstName, String teamMemberADENT) {
		getFirstRowofTableOnSelectATeamMemberPopUpInCreateEditCorrespondentBankListPage().wait(9000);
		List <WebElement> nameLinks = driver.findElements(By.xpath("//*[@id='DataTables_Table_0']/ tbody/tr/td[1]"));
		for(int i=0; i<nameLinks.size(); i++) {
			String expectedName = nameLinks.get(i).getText();
			if(expectedName.equalsIgnoreCase(teamMemberLastName+" "+teamMemberFirstName)) {
				String aDENtLink = driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+(i+1)+"]/td[3]")).getText();
				if (aDENtLink.equals(teamMemberADENT)) {
					nameLinks.get(i).click();
					break;					
				}					
			}
		}			
	}

	public boolean verifiesOtherBankLoansCreditExtensionsLoanIsAddedInsiderSurveyPage(
			String nameOfBank, String borrower, String productCreditType, String exposureLoanAmount, String outstandingBalance, String collateral, String ifSecured,
			String maturityDateApplicability, String expectedMaturityDate, String interestRateIndex, String interestRateSpread, String ifPreferential) {
		Thread.sleep(500);
		//		SyncUtil.sleep(500);
		List<WebElement> actualNameOfBanks = driver.findElements(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr/td[2]"));
		for(int i =0; i<actualNameOfBanks.size(); i++) {
			String actualNameOfBank = actualNameOfBanks.get(i).getText();
			if(actualNameOfBank.equals(nameOfBank)) {
				String acutalBorrower = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr["+(i+1)+"]/td[3]")).getText().trim();
				String acutalProductCreditType = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr["+(i+1)+"]/td[4]")).getText().trim();
				String acutalExposureLoanAmount = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr["+(i+1)+"]/td[5]")).getText().trim();
				String acutalOutstandingBalance = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr["+(i+1)+"]/td[6]")).getText().trim();
				String acutalChangeType = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr["+(i+1)+"]/td[7]")).getText().trim();
				if(acutalBorrower.equals(borrower) && acutalProductCreditType.equals(productCreditType) && acutalExposureLoanAmount.equals(exposureLoanAmount) 
						&& acutalOutstandingBalance.equals(outstandingBalance) && acutalChangeType.equals(collateral)) {
					driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr["+(i+1)+"]/td[1]")).click();
					String [] words = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr/td/div/div[1]")).getText().trim().split("\n");
					String actualCollateral = words[1];
					String acutalIfSecure;
					String expectedIfSecure;
					String ifSecure = driver.findElement(By.xpath("//*[@id='review-tblOtherLoansCrExtsInfo']/tbody/tr/td/div/div[2]")).getText();
					char [] a = ifSecure.toCharArray();					
					if(a.length > 33) {String [] ifSecureArray = ifSecure.split("\n");	acutalIfSecure = ifSecureArray[1];} 
					else {acutalIfSecure ="";}

					if(collateral.equals("Unsecured")) {
						expectedIfSecure = "";
					}
					else {
						expectedIfSecure = ifSecured;
					}
					String [] words2 = driver. findElement (By.xpath("//*[@id='review-tbl0therLoansCrExtsInfo']/tbody/tr/td/div/div[3]")).getText().trim().split("\n");
					String actualOriginationDate = words2[1];
					String [] words3 = driver.findElement(By.xpath("//*[@id='review-tbl0therLoansCrExtsInfo']/tbody/tr/td/div/div[4]")).getText().trim().split("\n");
					String actualMaturityDateApplicability = words3[1];
					String actualMaturityDate;
					String expectedIMaturityDate;
					String maturityDate = driver.findElement(By.xpath("//*[@id='review-tbl0therLoansCrExtsInfo']/tbody/tr/td/div/div[5]")).getText();
					char [] b = maturityDate.toCharArray();
					if(b.length>28) {
						String[] maturityDateArray = maturityDate.split("\n");
						actualMaturityDate = maturityDateArray[1];
					}
					else {
						actualMaturityDate ="";
					}
					if(maturityDateApplicability.equals("Open Ended")) {
						expectedIMaturityDate ="";
					}
					else {
						expectedIMaturityDate = todaysDate();
					}
					String [] words5 = driver.findElement(By.xpath("//*[@id='review-tbl0therLoansCrExtsInfo']/tbody/tr/td/div/div[6]")).getText().trim().split("\n");
					String actualInterestRateIndex = words5[1];
					String [] words6 = driver.findElement(By.xpath("//*[@id='review-tbl0therLoansCrExtsInfo']/tbody/tr/td/div/div[7]")).getText().trim().split("\n");
					String actualInterestRateSpread = words6[1];
					String [] words7 = driver.findElement(By.xpath("//*[@id='review-tbl0therLoansCrExtsInfo']/tbody/tr/td/div/div[8]")).getText().trim().split("\n");
					String actuallfPreferential = words7[1];
					if(actualCollateral.equals(collateral) && acutalIfSecure.equals(expectedIfSecure) && actualOriginationDate.equals(todaysDate())
							&& actualMaturityDateApplicability.equals(maturityDateApplicability) && actualMaturityDate.equals(expectedMaturityDate)
							&& actualInterestRateIndex.equals(interestRateIndex) && actualInterestRateSpread.equals(interestRateSpread)
							&& actuallfPreferential.equals(ifPreferential)){
						driver.findElement(By.xpath("//*[@ld='review-tblOtherLoansCrExtsInfo]/tbody/tr["+(i+1)+"]/td[1]")).click();
						return true;
					}
				}
			}
		}
		Reporter.log("Expected result not found", MessageTypes.Pass);
		return false;
	}

	public void checkCheckboxesOneByOneAndVerify() {
		List <WebElement> checkboxesSize = driver.findElements(By.xpath("//div[@class='col-xs-12 col-sm-12 co1-1g-12 col-md-12']/div/div/input"));
		for(int i=0; i<checkboxesSize.size(); i++) {
			List <WebElement> checkboxesK = driver.findElements(By.xpath("//div[@class='col-xs-12 col-sm-12 col-1g-12 col-nd-12']/div/div/input"));
			for (int k=0; k<i; k++) {
				if(k<i) {
					WebDriverTestCase.assertTrue(checkboxesK.get(k).isSelected(), "Element is NOT selected", "Element is selected");
					Reporter. log("Expected result found", MessageTypes.Fail);
				}
				else {
					WebDriverTestCase.assertFalse(checkboxesK.get(k).isSelected(), "Element is selected", "Element is NOT selected");
					Reporter.log("Expected result found", MessageTypes.Fail);
				}
			}
			List <WebElement> checkboxes = driver.findElements(By.xpath("//div[@class='col-xs-12 col-sm-12 col-1g-12 col-md-12']/div/div/input"));
			checkboxes.get(i).click();
			getSaveButtonInPreferencesPage().click();
			SyncUtil.sleep(1500);
			getPreferencesLinkHomePage().click();
			SyncUtil.sleep(1500);
			List <WebElement> checkboxesj = driver.findElements(By.xpath("//div[@class ='col-xs-12 col-sm-12 col-1g-12 col-nd-12']/div/div/input"));
			for(int j=1; j<checkboxesSize.size(); j++) {
				if (j==i) {
					WebDriverTestCase.assertTrue(checkboxesj.get(j).isSelected(), "Element is not selected", "Element is selected");
					Reporter.log("Expected result found", MessageTypes.Pass);
					break;
				}
				else {
					WebDriverTestCase.assertFalse(checkboxesj.get(j).isSelected(), "Element is selected", "Element is NOT selected");
					Reporter.log("Expected result found", MessageTypes.Fail);
				}
			}
		}
	}

	private WebElement getPreferencesLinkHomePage() {
		return null;
	}
	private WebElement getSaveButtonInPreferencesPage() {
		return null;
	}
	private String getCurrentDateFormat(String string) {
		return null;
	}
	public boolean actualDateIsPastDate(String actualDate) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date1 = sdf.parse(actualDate);
			Date date2 = sdf.parse(getCurrentDateFormat("MM/dd/yyyy"));
			System.out.println("Date1:"+sdf.format(date1)); 
			System.out.println("Date2:"+sdf.format(date2)) ;
			if (date1.after(date2)){
				Reporter.log("Expected result found", MessageTypes.Pass);
				return true;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		Reporter.log("Excepted result found", MessageTypes.Fail);
		return false;
	}

	public boolean verifyE1ementIsNotDisplayed(WebElement element) {
		Duration duration = Duration.ofSeconds(60);
		WebDriverWait wait = new WebDriverWait(new WebDriverTestBase().getDriver(), duration);
		wait.until(ExpectedConditions.invisibilityOf(element));

		if(element.isDisplayed()) {		
			Reporter.log("Expected result found", MessageTypes.Fail);
			return false;
		}
		else {

		}
	}
	public void verifyFieldTextUsingJavaScript(WebElement element, String expectedText) {
		//		QAFExtendedWebDriver driver = WebDriverTestBase().getDriver();
		Object theTextIwant = ((JavascriptExecutor)driver).executeScript("return arguments[]0.value;", element);
		String actualText = theTextIwant.toString().trim();
		WebDriverTestCase.assertTrue(actualText.equalsIgnoreCase(expectedText), actualText+"Text Does Not Match", actualText+"Text Does Match");		
	}
	public void verifyTextWithEqualsIgnoreCase(WebElement element, String expectedValue) {
		SyncUtil.sleep(500);
		String actualValue = element.getText();
		WebDriverTestCase.assertTrue(actualValue.equalsIgnoreCase(expectedValue), "ExpectedValue is Not present", "ExpectedValue is present");	
	}





}
