package selenium_Grid;

import org.testng.annotations.Test;

public class SwiggyTest extends BaseclassGrid{
	@Test
	public void swiggyTest() throws InterruptedException
	{
		driver.get("https://www.swiggy.com/");
		Thread.sleep(3000);
	}
	

}
