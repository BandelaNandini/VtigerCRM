package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import pomPages.PageObjectManager;

public class CreateAndDeleteLeadTest extends BaseClass {
PageObjectManager pageObjects;
	@Test
	public void createAndDeleteLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(pageObjects.getLeadsPage().getPageHeader(), "Leads");
		Thread.sleep(3000);
		pageObjects.getLeadsPage().clickPlusButton();
		soft.assertEquals(pageObjects.getCreateNewLeadPage().getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Delete lead", "LeadsTestData");
		String leadName = map.get("Lead Name") + jutil.generateRandom(100);
		pageObjects.getCreateNewLeadPage().setLastName(leadName);
		pageObjects.getCreateNewLeadPage().setCompanyName(map.get("Company"));
		pageObjects.getCreateNewLeadPage().clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(pageObjects.getNewLeadDetailPage().getPageHeader().contains(leadName));
		Thread.sleep(3000);
		pageObjects.getNewLeadDetailPage().clickLeadsLink();
		
		Thread.sleep(3000);
		pageObjects.getLeadsPage().deleteLead(web, leadName);
		web.handleAlert("OK");
		
		Thread.sleep(3000);
		soft.assertFalse(pageObjects.getLeadsPage().searchLeadname(leadName));
		soft.assertAll();
	}
}
