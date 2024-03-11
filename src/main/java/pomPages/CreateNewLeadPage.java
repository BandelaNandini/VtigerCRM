package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadPage {
	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageheader;

	@FindBy(name = "lastname")
	private WebElement lastnameTF;

	@FindBy(xpath = "//input[@name='company']")
	private WebElement companyTF;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	// Initialization
	public CreateNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getPageHeader() {
		return pageheader.getText();
	}

	public void setLastName(String leadName) {
		lastnameTF.sendKeys(leadName);
	}

	public void setCompanyName(String companyName) {
		companyTF.sendKeys(companyName);
	}

	public void clickSave() {
		saveButton.click();
	}

}
