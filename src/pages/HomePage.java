package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LaunchBrowser;

public class HomePage extends LaunchBrowser{
	public AuthenticationPage clickOnSignIn() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement signInElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header_user_info>a")));
		signInElement.click();
		System.out.println("Clicked on sign in");
		return new AuthenticationPage();
	}
}
