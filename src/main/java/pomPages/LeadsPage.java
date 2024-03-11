package pomPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_Utilities.WebDriverUtility;

public class LeadsPage {
	// Declaration
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//img[@alt='Create Lead...']")
	private WebElement plusButton;
	
	@FindBy(xpath = "//table[@class='lvt small']/tbody/tr[last()]/td[3]/a")
	private WebElement newLeadLink;

	private String leadCheckboxPath="//a[text()='%s']/parent::td/preceding-sibling::td/input";
	
	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteButton;
	
	
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr/td[3]/a")
	private List<WebElement> leadNames;
	
	
	// initialization
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getPageHeader() {
		return pageHeader.getText();
	}

	public void clickPlusButton() {
		plusButton.click();

	}

	public String getNewleadName() {
		return newLeadLink.getText();
	}
	
	public boolean searchLeadname(String leadname)
	{
		boolean status=false;
		for (WebElement name:leadNames)
		{
			if (name.getText().equals(leadname))
			{
				status=true;
				break;
			}
		}
		return status;
	}
	
	public void deleteLead(WebDriverUtility web,String leadname)
	{
		web.convertpathToWebElement(leadCheckboxPath, leadname).click();
		deleteButton.click();
	}

}
