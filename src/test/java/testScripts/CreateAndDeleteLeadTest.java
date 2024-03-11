package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;

public class CreateAndDeleteLeadTest extends BaseClass {

	@Test
	public void createAndDeleteLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(lead.getPageHeader(), "Leads");
		Thread.sleep(3000);
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Delete lead", "LeadsTestData");
		String leadName = map.get("Lead Name") + jutil.generateRandom(100);
		createLead.setLastName(leadName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSave();
		
		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(leadName));
		Thread.sleep(3000);
		newLead.clickLeadsLink();
		
		Thread.sleep(3000);
		lead.deleteLead(web, leadName);
		web.handleAlert("OK");
		
		Thread.sleep(3000);
		soft.assertFalse(lead.searchLeadname(leadName));
		soft.assertAll();

		
	}
}
