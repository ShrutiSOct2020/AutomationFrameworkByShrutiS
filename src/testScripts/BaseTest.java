package testScripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.LaunchBrowser;

public class BaseTest {
	@BeforeMethod
	public void openBrowser() throws InterruptedException {
		System.out.println("Step - Lauch the browser");
		LaunchBrowser.start();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("Step - Close the session");
		LaunchBrowser.close();
	}

}
