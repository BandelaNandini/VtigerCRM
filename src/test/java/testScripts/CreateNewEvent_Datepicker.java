package testScripts;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewEvent_Datepicker {
	@Test
	public void createEventTest() throws InterruptedException {
		Random random = new Random();
		int randomNum = random.nextInt(100);

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();

		WebElement quickCreateDD = driver.findElement(By.id("qccombo"));
		Select s = new Select(quickCreateDD);
		s.selectByValue("Events");

		driver.findElement(By.name("subject")).sendKeys("Expo" + randomNum);
		driver.findElement(By.id("jscal_trigger_date_start")).click();

		int reqYear = 2026;
		int reqMonth = 3;
		String reqDate = "18";

		String actMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
		String[] str = actMonthYear.split(", ");
		int actYearInNum = Integer.parseInt(str[1]);

		while(actYearInNum < reqYear) {
			driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']")).click();
			actMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
			str = actMonthYear.split(", ");
			actYearInNum = Integer.parseInt(str[1]);
		}

		int actMonthInNum = DateTimeFormatter
							.ofPattern("MMMM")
							.withLocale(Locale.ENGLISH)
							.parse(str[0])
							.get(ChronoField.MONTH_OF_YEAR);

		while(actMonthInNum > reqMonth) {
			driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='‹']")).click();
			actMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
			str = actMonthYear.split(", ");
			actMonthInNum = DateTimeFormatter
					.ofPattern("MMMM")
					.withLocale(Locale.ENGLISH)
					.parse(str[0])
					.get(ChronoField.MONTH_OF_YEAR);
		}

		while(actMonthInNum < reqMonth) {
			driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='›']")).click();
			actMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")).getText();
			str = actMonthYear.split(", ");
			actMonthInNum = DateTimeFormatter
					.ofPattern("MMMM")
					.withLocale(Locale.ENGLISH)
					.parse(str[0])
					.get(ChronoField.MONTH_OF_YEAR);
		}

		driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='day' and text()='"+reqDate+"']")).click();
		Thread.sleep(3000);
		WebElement endDate = driver.findElement(By.id("jscal_field_due_date"));
		endDate.clear();
		endDate.sendKeys("2026-06-18");
		driver.findElement(By.xpath("//input[@value='  Save']")).click();
		driver.close();
	}
}


