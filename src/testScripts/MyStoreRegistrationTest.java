package testScripts;

import java.io.IOException;
import java.util.ArrayList;
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

public class MyStoreRegistrationTest extends BaseTest{

	@Test
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
	
	@Test(dataProvider="CreateAccountDataProvider")
	public void createAccountDataDrivenTest(String email, String gender, String firstName, String lastName, String password, String days, 
			String months, String year, String company, String address1, String city, String state, String postcode, String additionalInfo,
			String hPhone, String mNumber, String aliasAddress) {

		HomePage homePage = new HomePage();
		System.out.println("Step - Click on signin and navigate to Authentication Page ");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();

		System.out.println("Verify - Authentication Header is displayed");
		boolean authenticationHeaderFlag = authenticationPage.isAuthenticationheaderVisible();
		Assert.assertTrue(authenticationHeaderFlag, "Authentication Header not displayed");

		System.out.println("Step -  Enter the email id ");
		authenticationPage.enterEmailAddress(email);
		CreateAccountPage createAccount = authenticationPage.clickOnCreateAnAccount();

		System.out.println("Verify Create Account heading is displayed");
		boolean isHeadingDispalyed = createAccount.isAccountPageHeadingTextDisplayed();
		Assert.assertTrue(isHeadingDispalyed, "Create Account heading not displayed");

		System.out.println("Navigate to create account page");
		FillCreateAccountDetailsPojo accountDetailsPojo = new FillCreateAccountDetailsPojo();
		boolean mFlag = gender.equalsIgnoreCase("male") ? true : false;
		accountDetailsPojo.setMale(mFlag);
		accountDetailsPojo.setFirstName(firstName);
		accountDetailsPojo.setLastName(lastName);
		accountDetailsPojo.setPassword(password);
		accountDetailsPojo.setDays(days);
		accountDetailsPojo.setMonth(months);
		accountDetailsPojo.setYear(year);
		accountDetailsPojo.setCompany(company);
		accountDetailsPojo.setAddress1(address1);
		accountDetailsPojo.setCity(city);
		accountDetailsPojo.setState(state);
		accountDetailsPojo.setPostCode(postcode);
		accountDetailsPojo.setAdditionalInfo(additionalInfo);
		accountDetailsPojo.sethPhone(hPhone);
		accountDetailsPojo.setmNumber(mNumber);
		accountDetailsPojo.setAliasAddress(aliasAddress);

		createAccount.enterCreateAccountDetails(accountDetailsPojo);
		MyProfilePage myProfilePage = createAccount.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = firstName+" "+lastName;
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
	
	@DataProvider(name="CreateAccountDataProvider")
	public String[][] getDataForCreateAccount() throws IOException{
		return ExcelOperation.getExcelData("data1.xls", "CreateAccount");
	}
	


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
}
