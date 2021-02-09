package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {
	protected static WebDriver driver;
	
	public static void start() throws InterruptedException {
		Thread.sleep(3000);
		String path ="./resources/windows/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		System.out.println("Navigate to Application");
	}
}
