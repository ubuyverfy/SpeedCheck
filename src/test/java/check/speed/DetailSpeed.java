package check.speed;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailSpeed {

	public static List<String> randomWebElements = new ArrayList<>();
	public int totalcount = 0;

	public void getUrls() throws InterruptedException {

		for (String hitURl : randomWebElements) {
			try {
				long startDetailTime = System.currentTimeMillis();
				LunchBrowser.driver.get(hitURl);
				WebDriverWait wait = new WebDriverWait(LunchBrowser.driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='title h1 mb-2']")));
				long endDetailTime = System.currentTimeMillis();
				long timeDiff = endDetailTime - startDetailTime;
				System.out.println(timeDiff);
				ConsoleData.printStream.append("Detail page load time=" + timeDiff + " MilliSeconds--"+hitURl +"\r\n\r\n");
				totalcount++;

				if (totalcount == 9) {
					Thread.sleep(1000);
					EmailSent emailsent = new EmailSent();
					emailsent.sendingNewEmail();
					totalcount = 0;
					ConsoleData cd = new ConsoleData();
					cd.searchConsoleData();
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		LunchBrowser.driver.quit();
		

	}
}
