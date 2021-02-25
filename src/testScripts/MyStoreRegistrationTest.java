package testScripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.LaunchBrowser;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.FillCreateAccountDetailsPojo;
import util.ExcelOperation;

public class MyStoreRegistrationTest extends BaseTest {

	
	//1.a Getting data from Pojo with help of data provider
	@Test(dataProvider = "CreateAccountDataProviderPojo")
	public void createAccountDataDrivenTest(FillCreateAccountDetailsPojo accountDetailsPojo) {

		HomePage homePage = new HomePage();
		System.out.println("Step - Click on signin and navigate to Authentication Page ");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationheaderVisible();
		Assert.assertTrue(authenticationHeaderFlag, "Authentication Header not displayed");

		System.out.println("Step -  Enter the email id ");
		authenticationPage.enterEmailAddress(accountDetailsPojo.getEmail());
		CreateAccountPage createAccount = authenticationPage.clickOnCreateAnAccount();

		System.out.println("Verify Create Account heading is displayed");
		boolean isHeadingDispalyed = createAccount.isAccountPageHeadingTextDisplayed();
		Assert.assertTrue(isHeadingDispalyed, "Create Account heading not displayed");

		System.out.println("Navigate to create account page");

		createAccount.enterCreateAccountDetails(accountDetailsPojo);
		MyProfilePage myProfilePage = createAccount.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = accountDetailsPojo.getFirstName() + " " + accountDetailsPojo.getLastName();
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
    //1.b Data provider capturing Pojo class
	@DataProvider(name = "CreateAccountDataProviderPojo")
	public Object[][] getDataForCreateAccountPojo() throws IOException {
		String[][] data = ExcelOperation.getExcelData("data1.xls", "CreateAccount");
		Object[][] output = new Object[data.length][1];
		for (int index = 0; index < data.length; index++) {
			FillCreateAccountDetailsPojo accountDetailsPojo = new FillCreateAccountDetailsPojo();
			accountDetailsPojo.setEmail(data[index][0]);
			boolean mFlag = data[index][1].equalsIgnoreCase("male") ? true : false;
			accountDetailsPojo.setMale(mFlag);
			accountDetailsPojo.setFirstName(data[index][2]);
			accountDetailsPojo.setLastName(data[index][3]);
			accountDetailsPojo.setPassword(data[index][4]);
			accountDetailsPojo.setDays(data[index][5]);
			accountDetailsPojo.setMonth(data[index][6]);
			accountDetailsPojo.setYear(data[index][7]);
			accountDetailsPojo.setCompany(data[index][8]);
			accountDetailsPojo.setAddress1(data[index][9]);
			accountDetailsPojo.setCity(data[index][10]);
			accountDetailsPojo.setState(data[index][11]);
			accountDetailsPojo.setPostCode(data[index][12]);
			accountDetailsPojo.setAdditionalInfo(data[index][13]);
			accountDetailsPojo.sethPhone(data[index][14]);
			accountDetailsPojo.setmNumber(data[index][15]);
			accountDetailsPojo.setAliasAddress(data[index][16]);
			output[index][0] = accountDetailsPojo;
		}
		return output;
	}
	
	/*//2.a Getting data from data provider in HasMap
	@Test(dataProvider="CreateAccountDataProviderMap")
	public void createAccountWithMapTest(HashMap<String,String> hm) {
		System.out.println(hm.get("email"));
		System.out.println(hm.get("gender"));
	}
	
	//2.b Data provider capturing HasMap
	@DataProvider(name = "CreateAccountDataProviderMap")
		public Object[][] getDataForCreateAccountMap() throws IOException {
			String[][] data = ExcelOperation.getExcelData("data1.xls", "CreateAccount");
			Object[][] output = new Object[data.length][1];
			for (int index = 0; index < data.length; index++) {
				HashMap<String,String> hm = new HashMap<String,String>();
				hm.put("email", data[index][0]);
				hm.put("gender", data[index][1]);
				hm.put("firstName", data[index][2]);
				hm.put("lastName", data[index][3]);
				hm.put("Password", data[index][4]);
				hm.put("Days", data[index][5]);
				hm.put("Month", data[index][6]);
				hm.put("Year", data[index][7]);
				hm.put("Company", data[index][8]);
				hm.put("Address1", data[index][9]);
				hm.put("City", data[index][10]);
				hm.put("State", data[index][11]);
				hm.put("PostCode", data[index][12]);
				hm.put("AdditionalInfo", data[index][13]);
				hm.put("hPhone", data[index][14]);
				hm.put("mNumber", data[index][15]);
				hm.put("AliasAddress", data[index][16]);
				output[index][0] = hm;
			}
			return output;
		}*/
	
	// validation when fields are missing then proper error message should come
	@Test
	public void myStoreUIValidationTest() throws InterruptedException {
		System.out.println("Step - Lauch the browser");
		LaunchBrowser.start();
		HomePage homePage = new HomePage();
		System.out.println("Step - Click on signin and navigate to Authentication Page ");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationheaderVisible();
		Assert.assertTrue(authenticationHeaderFlag, "Authentication Header not displayed");

		System.out.println("Step -  Enter the email id ");
		authenticationPage.enterEmailAddress("shruti05@gmail.com");
		CreateAccountPage createAccount = authenticationPage.clickOnCreateAnAccount();

		System.out.println("Verify Create Account heading is displayed");
		boolean isHeadingDispalyed = createAccount.isAccountPageHeadingTextDisplayed();
		Assert.assertTrue(isHeadingDispalyed, "Create Account heading not displayed");

		System.out.println("Navigate to create account page");
		FillCreateAccountDetailsPojo accountDetailsPojo = new FillCreateAccountDetailsPojo();

		createAccount.enterCreateAccountDetails(accountDetailsPojo);
		createAccount.clickOnRegistration();

		List<String> expectedErrorMessages = new ArrayList<String>();
		expectedErrorMessages.add("There are 8 errors");
		expectedErrorMessages.add("You must register at least one phone number.");
		expectedErrorMessages.add("lastname is required.");
		expectedErrorMessages.add("firstname is required.");
		expectedErrorMessages.add("passwd is required.");
		expectedErrorMessages.add("address1 is required.");
		expectedErrorMessages.add("city is required.");
		expectedErrorMessages.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		expectedErrorMessages.add("This country requires you to choose a State.");

		List<String> actualErrorMessages = createAccount.getErrorMessage();
		Assert.assertEquals(actualErrorMessages, expectedErrorMessages);
	}
	// Directly entering data in test case
	/*@Test
	public void myStoreLoginTest() throws InterruptedException {

		HomePage homePage = new HomePage();
		System.out.println("Step - Click on signin and navigate to Authentication Page ");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationheaderVisible();
		Assert.assertTrue(authenticationHeaderFlag, "Authentication Header not displayed");

		System.out.println("Step -  Enter the email id ");
		authenticationPage.enterEmailAddress("shruti08@gmail.com");
		CreateAccountPage createAccount = authenticationPage.clickOnCreateAnAccount();

		System.out.println("Verify Create Account heading is displayed");
		boolean isHeadingDispalyed = createAccount.isAccountPageHeadingTextDisplayed();
		Assert.assertTrue(isHeadingDispalyed, "Create Account heading not displayed");

		System.out.println("Navigate to create account page");
		FillCreateAccountDetailsPojo accountDetailsPojo = new FillCreateAccountDetailsPojo();

		accountDetailsPojo.setMale(true);
		accountDetailsPojo.setFirstName("Shruti");
		accountDetailsPojo.setLastName("Sattigeri");
		accountDetailsPojo.setPassword("ShrutiiS4656");
		accountDetailsPojo.setDays("12");
		accountDetailsPojo.setMonth("2");
		accountDetailsPojo.setYear("1991");
		accountDetailsPojo.setCompany("PTC");
		accountDetailsPojo.setAddress1("Kuamr Primavera");
		accountDetailsPojo.setCity("Pune");
		accountDetailsPojo.setState("California");
		accountDetailsPojo.setPostCode("41101");
		accountDetailsPojo.setAdditionalInfo("NA");
		accountDetailsPojo.sethPhone("8796665678");
		accountDetailsPojo.setmNumber("879005678");
		accountDetailsPojo.setAliasAddress("");

		createAccount.enterCreateAccountDetails(accountDetailsPojo);
		MyProfilePage myProfilePage = createAccount.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = "Shruti Sattigeri";
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
*/
	//Getting data from data provider and setting data in test case
	/*
	 * @Test(dataProvider="CreateAccountDataProvider") public void
	 * createAccountDataDrivenTest(String email, String gender, String firstName,
	 * String lastName, String password, String days, String months, String year,
	 * String company, String address1, String city, String state, String postcode,
	 * String additionalInfo, String hPhone, String mNumber, String aliasAddress) {
	 * 
	 * HomePage homePage = new HomePage(); System.out.
	 * println("Step - Click on signin and navigate to Authentication Page ");
	 * AuthenticationPage authenticationPage = homePage.clickOnSignIn();
	 * 
	 * System.out.println("Verify - Authentication Header is displayed"); boolean
	 * authenticationHeaderFlag =
	 * authenticationPage.isAuthenticationheaderVisible();
	 * Assert.assertTrue(authenticationHeaderFlag,
	 * "Authentication Header not displayed");
	 * 
	 * System.out.println("Step -  Enter the email id ");
	 * authenticationPage.enterEmailAddress(email); 
	 * CreateAccountPage createAccount = authenticationPage.clickOnCreateAnAccount();
	 * 
	 * System.out.println("Verify Create Account heading is displayed"); boolean
	 * isHeadingDispalyed = createAccount.isAccountPageHeadingTextDisplayed();
	 * Assert.assertTrue(isHeadingDispalyed,
	 * "Create Account heading not displayed");
	 * 
	 * System.out.println("Navigate to create account page");
	 * FillCreateAccountDetailsPojo accountDetailsPojo = new FillCreateAccountDetailsPojo(); 
	 * boolean mFlag = gender.equalsIgnoreCase("male") ? true : false;
	 * accountDetailsPojo.setMale(mFlag);
	 * accountDetailsPojo.setFirstName(firstName);
	 * accountDetailsPojo.setLastName(lastName);
	 * accountDetailsPojo.setPassword(password); accountDetailsPojo.setDays(days);
	 * accountDetailsPojo.setMonth(months); accountDetailsPojo.setYear(year);
	 * accountDetailsPojo.setCompany(company);
	 * accountDetailsPojo.setAddress1(address1); accountDetailsPojo.setCity(city);
	 * accountDetailsPojo.setState(state); accountDetailsPojo.setPostCode(postcode);
	 * accountDetailsPojo.setAdditionalInfo(additionalInfo);
	 * accountDetailsPojo.sethPhone(hPhone); accountDetailsPojo.setmNumber(mNumber);
	 * accountDetailsPojo.setAliasAddress(aliasAddress);
	 * 
	 * createAccount.enterCreateAccountDetails(accountDetailsPojo); MyProfilePage
	 * myProfilePage = createAccount.clickOnRegistration(); String actual =
	 * myProfilePage.getHeaderText(); String expected = firstName+" "+lastName;
	 * Assert.assertEquals(actual, expected, "Verification of headertext failed"); }
	 * 
	 * @DataProvider(name="CreateAccountDataProvider") public String[][]
	 * getDataForCreateAccount() throws IOException{ return
	 * ExcelOperation.getExcelData("data1.xls", "CreateAccount"); }
	 */
}
