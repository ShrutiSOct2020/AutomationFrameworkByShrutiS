package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.LaunchBrowser;

public class MyProfilePage extends LaunchBrowser {
	
	public String getHeaderText() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String headerText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info span")))
				.getText();
		return headerText;
	}

	public String getLoginName() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String loginName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account>span")))
				.getText();
		return loginName;
	}
}
