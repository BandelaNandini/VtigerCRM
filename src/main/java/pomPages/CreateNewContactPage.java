package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_Utilities.WebDriverUtility;

public class CreateNewContactPage {
	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageheader;

	@FindBy(name = "lastname")
	private WebElement lastnameTF;

	@FindBy(xpath = "//img[contains(@onclick,\"Accounts\")]")
	private WebElement orgPlusButton;

	@FindBy(xpath = "(//input[normalize-space(@value)='Save'])[2]")
	private WebElement saveButton;

	private String orgPath = "//a[text()='%S']";

	// Initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getPageHeader() {
		return pageheader.getText();
	}

	public void setLastName(String contactName) {
		lastnameTF.sendKeys(contactName);
	}

	public void selectExistingOrg(WebDriverUtility web, String orgName) throws InterruptedException {
		orgPlusButton.click();
		//Thread.sleep(3000);
		String parentID=web.getParentWindow();
		web.switchToChildBrowser();
		web.convertpathToWebElement(orgPath, orgName).click();
		web.switchToWindow(parentID);
	}

	public void clickSave() {
		saveButton.click();
	}

}
