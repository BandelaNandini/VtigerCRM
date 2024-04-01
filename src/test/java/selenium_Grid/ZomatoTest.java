package selenium_Grid;

import org.testng.annotations.Test;

public class ZomatoTest extends BaseclassGrid{
	@Test
	public void zomatoTest() throws InterruptedException {
		driver.get("https://www.zomato.com/");;
		Thread.sleep(3000);
	}

}
