package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.LaunchBrowser;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;

public class MyStoreRegistrationTest {

	@Test
	public void automationPracticeLogin() {
		LaunchBrowser.start();
		HomePage homePage = new HomePage();
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("Navigate to Authentication Page");
		authenticationPage.enterEmailAddress("shrutis@gmail.com");
		CreateAccountPage createAccount = authenticationPage.clickOnCreateAnAccount();
		System.out.println("Navigate to create account page");
		createAccount.enterCreateAccountDetails();
		MyProfilePage myProfilePage = createAccount.clickOnRegistration();
		String actual = myProfilePage.getHeaderText();
		String expected = "automation automation";
		Assert.assertEquals(actual, expected, "Verification of headertext failed");
	}
}
