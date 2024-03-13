package mvnPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {
	WebDriver driver=null;
	String browser = System.getProperty("Browser");
	String url = System.getProperty("URL");
	@Test
	public void launchBrowserTest() throws InterruptedException {switch (browser) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		break;
	case "edge":
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		break;
	default:
		System.out.println("Invalid Browser");

	}
	driver.manage().window().maximize();
	driver.get(url);
	Thread.sleep(3000);
	driver.close();
}
}