package check.speed;

import java.io.IOException;

import org.testng.annotations.Test;

public class MainClassMethod {

	@Test
	public void CalledMethod() throws IOException, InterruptedException {

		LunchBrowser lb = new LunchBrowser();
		lb.browserLunch();
		ConsoleData cd= new ConsoleData();
		cd.searchConsoleData();
		SearchSpeed speed=new SearchSpeed();
		speed.search_speed();

	}

}
