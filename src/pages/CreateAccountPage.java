package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LaunchBrowser;
import pojo.FillCreateAccountDetailsPojo;

public class CreateAccountPage extends LaunchBrowser {
	WebDriverWait wait = new WebDriverWait(driver, 30);

	public boolean isAccountPageHeadingTextDisplayed() {
		return wait.until(ExpectedConditions.textToBe(By.cssSelector("#noSlide h1"), "CREATE AN ACCOUNT"));
	}

	private void selectGender(boolean isMale) {
		System.out.println("Step- Select title");
		WebElement titleElement;
		// ternary operator
		titleElement = isMale ? wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-id_gender1")))
				: wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-id_gender2")));
		wait.until(ExpectedConditions.elementToBeClickable(titleElement)).click();
		titleElement.click();
	}

	private void enterFirstName(String firstName) {
		System.out.println("Step - Enter First Name");
		if (firstName != null) {
			driver.findElement(By.xpath("//input[@name='customer_firstname']")).sendKeys(firstName);
		} else
			System.out.println("First name filed is blank");
	}

	private void enterlastname(String lastName) {
		System.out.println("Step - Enter Last Name");
		if (lastName != null) {
			driver.findElement(By.xpath("//input[@name='customer_lastname']")).sendKeys(lastName);
		} else
			System.out.println("LastName field is blank");
	}

	private void enterpassword(String password) {
		System.out.println("Step - Enter Password");
		if (password != null) {
			driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys(password);
		} else
			System.out.println("Password field is blank");
	}

	private void selectBirthDate(String days) {
		System.out.println("Step- Select the birthday from dropdown");
		if (days != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-days")))).click();
			Select s = new Select(driver.findElement(By.xpath("//select[@name='days']")));
			s.selectByValue(days);
		} else
			System.out.println("Days not selected");
	}

	private void selectBirthMonth(String month) {
		System.out.println("Step - Select Birth month from dropdown");
		if (month != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months"))).click();
			Select s = new Select(driver.findElement(By.id("months")));
			s.selectByValue(month);
		} else
			System.out.println("Month not selected");
	}

	private void selectBirthYear(String year) {
		System.out.println("Step - Select Birth year from dropdown");
		if (year != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years"))).click();
			Select s = new Select(driver.findElement(By.id("years")));
			s.selectByValue(year);
		} else
			System.out.println("Year not selected");
	}

	private void enterCompany(String company) {
		System.out.println("Step - Enter Company Name ");
		if (company != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("company"))).sendKeys("ABC");
			driver.findElement(By.id("company")).sendKeys(company);
		} else
			System.out.println("Company field is blank");

	}

	private void enterAddress(String address) {
		System.out.println("Step - Enter Address Name ");
		if (address != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address1"))).sendKeys("650
			// Grassmere park");
			driver.findElement(By.id("address1")).sendKeys(address);
		} else
			System.out.println("address field is blank");
	}

	private void enterCity(String city) {
		System.out.println("Step - Enter City Name ");
		if (city != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys("nashville");
			driver.findElement(By.id("city")).sendKeys(city);
		} else
			System.out.println("city field is blank");
	}

	private void selectState(String state) {
		System.out.println("Step - Select State from dropdown");
		if (state != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("uniform-id_state")))).click();
			Select s = new Select(driver.findElement(By.xpath("//select[@id='id_state']")));
			s.selectByVisibleText(state);
		} else
			System.out.println("state field is blank");
	}

	private void enterPostcode(String postcode) {
		System.out.println("Step - Enter Postcode");
		if (postcode != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postcode"))).sendKeys("37211");
			driver.findElement(By.id("postcode")).sendKeys(postcode);
		} else
			System.out.println("postcode field is blank");
	}

	private void enterAdditionalInfo(String additionalInfo) {
		System.out.println("Step - Enter Additional information ");
		if (additionalInfo != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("other"))).sendKeys("NA");
			driver.findElement(By.id("other")).sendKeys(additionalInfo);
		} else
			System.out.println("Additional info field is blank");
	}

	private void enterHomeMobileNumber(String hNumber) {
		System.out.println("Step - Enter Home mobile number");
		if (hNumber != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("8905714840");
			driver.findElement(By.id("phone")).sendKeys(hNumber);
		} else
			System.out.println("Home mobile number is blank");
	}

	private void enterMobilenumber(String mNumber) {
		System.out.println("Step - Enter Mobile number");
		if (mNumber != null) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone_mobile"))).sendKeys("8905714840");
			driver.findElement(By.id("phone_mobile")).sendKeys(mNumber);
		} else
			System.out.println("Mobile number field is blank");
	}

	public void enterCreateAccountDetails(FillCreateAccountDetailsPojo accountDetailsPojo) {
		selectGender(accountDetailsPojo.isMale());
		enterFirstName(accountDetailsPojo.getFirstName());
		enterlastname(accountDetailsPojo.getLastName());
		enterpassword(accountDetailsPojo.getPassword());
		selectBirthDate(accountDetailsPojo.getDays());
		selectBirthMonth(accountDetailsPojo.getMonth());
		selectBirthYear(accountDetailsPojo.getYear());
		enterCompany(accountDetailsPojo.getCompany());
		enterAddress(accountDetailsPojo.getAddress1());
		enterCity(accountDetailsPojo.getCity());
		selectState(accountDetailsPojo.getState());
		enterPostcode(accountDetailsPojo.getPostCode());
		enterAdditionalInfo(accountDetailsPojo.getAdditionalInfo());
		enterHomeMobileNumber(accountDetailsPojo.gethPhone());
		enterMobilenumber(accountDetailsPojo.getmNumber());
	}

	public MyProfilePage clickOnRegistration() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		System.out.println("Details Registered in Application");
		return new MyProfilePage();
	}
	
	public List<String> getErrorMessage(){
		List<WebElement> listOfErrorElements = driver.findElements(By.cssSelector("ol>li"));
		List<String> listOfErrorText = new ArrayList<String>();
		String errorHeadText = driver.findElement(By.cssSelector(".alert.alert-danger>p")).getText();
		listOfErrorText.add(errorHeadText);
		for(WebElement ele : listOfErrorElements) {
			listOfErrorText.add(ele.getText());
		}
		return listOfErrorText;
	}
}
