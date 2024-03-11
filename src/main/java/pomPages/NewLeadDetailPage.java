package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLeadDetailPage {

	// declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;

	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement leadsLink;

	@FindBy(name = "Duplicate")
	private WebElement duplicateButton;

	// intialization
	public NewLeadDetailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// utilization
	public String getPageHeader() {
		return pageHeader.getText();

	}
	public void clickLeadsLink() {
		leadsLink.click();
	}
	
	public void clickDuplicate()
	{
		duplicateButton.click();
	}

}
