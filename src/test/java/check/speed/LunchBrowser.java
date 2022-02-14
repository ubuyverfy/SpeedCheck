package check.speed;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LunchBrowser {

	public static WebDriver driver;
	WebDriverWait wait;
	public static void browserLunch() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("Chrome lunched");
		driver.get("https://www.a.ubuy.com.kw/en/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
	}
	
	/*
	 * public void create_file() throws IOException {
	 * 
	 * File file = new File(System.getProperty("user.dir")+"/textfile.txt");
	 * file.createNewFile(); PrintStream ps = new PrintStream(file);
	 * ps.append(driver.getCurrentUrl());
	 * 
	 * }
	 */
	
}
