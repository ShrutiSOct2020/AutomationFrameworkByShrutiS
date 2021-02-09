package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LaunchBrowser;

public class CreateAccountPage extends LaunchBrowser {
	 
	WebDriverWait wait;
	public void enterCreateAccountDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1"))).click();
		System.out.println("Select Mr. as title");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_firstname"))).sendKeys("automation");
		System.out.println("Enter First Name");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer_lastname"))).sendKeys("automation");
		System.out.println("Enter Last Name");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd"))).sendKeys("automation");
		System.out.println("Enter Password");

		//wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-days")))).click();
		Select s = new Select(driver.findElement(By.id("days")));
		s.selectByValue("6");
		System.out.println("Birthdate selected from drop down");

		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months"))).click();
		s = new Select(driver.findElement(By.id("months")));
		s.selectByValue("2");
		System.out.println("Birth month selected");

		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years"))).click();
		s = new Select(driver.findElement(By.id("years")));
		s.selectByValue("1997");
		System.out.println("Birth year selected");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("company"))).sendKeys("ABC");
		System.out.println("Company Name is entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address1"))).sendKeys("650 Grassmere park");
		System.out.println("Address Name is entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys("nashville");
		System.out.println("City Name is entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-id_state")))).click();
		s = new Select(driver.findElement(By.id("id_state")));
		s.selectByVisibleText("Tennessee");
		System.out.println("State is selected");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postcode"))).sendKeys("37211");
		System.out.println("Postcode is entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("other"))).sendKeys("NA");
		System.out.println("Other information is entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("8905714840");
		System.out.println("Home mobile number is entered");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone_mobile"))).sendKeys("8905714840");
		System.out.println("Mobile number is entered");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		System.out.println("Details Registered in Application");
	}
	public MyProfilePage clickOnRegistration() {
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		System.out.println("Details Registered in Application");
		return new MyProfilePage();
	}

}
