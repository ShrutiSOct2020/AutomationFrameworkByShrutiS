package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LaunchBrowser;
import pojo.FillCreateAccountDetailsPojo;

public class AuthenticationPage extends LaunchBrowser{
	
	public void enterEmailAddress(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")))
		.sendKeys(id);
		System.out.println("Enter email addressfor the create user name");
	}
	
	public CreateAccountPage clickOnCreateAnAccount() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		System.out.println("Clicked on the submit button to fill the other details");
		return new CreateAccountPage();
	}
	
	public boolean isAuthenticationheaderVisible() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Authentication']")));
		return element.isDisplayed();	
	}
	
	public MyProfilePage loginAccount(FillCreateAccountDetailsPojo enterLoginDetails) {
		enterLoginEmail(enterLoginDetails.getEmail());
		enterLoginPassword(enterLoginDetails.getPassword());
		clickOnSignIn();
		return new MyProfilePage();
	}
	
	private void enterLoginEmail(String emailId) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))).sendKeys(emailId);
		System.out.println("Enter email address for Login");
		
	}
	private void enterLoginPassword(String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passwd']"))).sendKeys(pwd);
		System.out.println("Enter password for Login");
	}
	private void clickOnSignIn() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#SubmitLogin"))).click();
		System.out.println("Clicked on Sign in button to login");
	}
}
