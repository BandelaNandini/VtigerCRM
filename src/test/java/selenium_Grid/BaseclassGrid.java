package selenium_Grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseclassGrid {

	protected RemoteWebDriver driver;
	@Parameters("BROWSER")
	@BeforeClass
	public void classSetup(String browser) throws MalformedURLException
	{
		URL url=new URL("http://192.168.10.1:7777/wd/hub");
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setPlatform(Platform.WINDOWS);
		switch (browser) {
		case "chrome":
			cap.setBrowserName("chrome");
			break;
		case "firefox":
			cap.setBrowserName("firefox");
			break;
		default:
			System.out.println("Invalid");
		}
		driver= new RemoteWebDriver(url,cap);
		driver.manage().window().maximize();
	}
	@AfterClass
	public void classteardown(){
		driver.close();
		
	}
}
