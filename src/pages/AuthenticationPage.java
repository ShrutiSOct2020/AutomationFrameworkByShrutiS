package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LaunchBrowser;

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
}
