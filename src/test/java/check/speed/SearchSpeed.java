package check.speed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchSpeed {

	public static HSSFRow row1;
	public static HSSFCell keywords;
	int count;

	public void search_speed() throws IOException {

		File file = new File(System.getProperty("user.dir") + "/UbuyExcel.xls");
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (int fetchKeyword = 1; fetchKeyword <= 7; fetchKeyword++) {

			try {
				int searchRandomNumber = (int) (Math.random() * sheet.getLastRowNum());
				System.out.println(searchRandomNumber);
				row1 = sheet.getRow(searchRandomNumber);
				keywords = row1.getCell(0);
				WebElement searchBar = LunchBrowser.driver.findElement(By.name("q"));
				searchBar.clear();
				searchBar.sendKeys(keywords.toString());
				long startTime = System.currentTimeMillis();
				searchBar.sendKeys(Keys.ENTER);
				WebDriverWait wait = new WebDriverWait(LunchBrowser.driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//img[starts-with(@data-src,'https://m.media-amazon.com')]")));
				count++;
				// Set Detail URL
				List<WebElement> detailUrl = LunchBrowser.driver.findElements(By
						.xpath("//img[starts-with(@data-src,'https://m.media-amazon.com')]/parent::figure/parent::a"));

				for (int detailUrlCount = 1; detailUrlCount <= 3; detailUrlCount++) {

					int detailRandomNumber = (int) (Math.random() * detailUrl.size());
					// System.out.println(detailRandomNumber);
					DetailSpeed.randomWebElements.add((detailUrl).get(detailRandomNumber).getAttribute("href"));

				}

				long endTime = System.currentTimeMillis();
				long timeDifference = endTime - startTime;
				ConsoleData.printStream.append(
						"List page load time for keyword" + "("+keywords+")=" + timeDifference + " MilliSeconds\r\n");
				System.out.println("count values:" + count);

				if (count == 7) {
					System.out.println("Get url called");
					DetailSpeed dspeed = new DetailSpeed();
					dspeed.getUrls();

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
