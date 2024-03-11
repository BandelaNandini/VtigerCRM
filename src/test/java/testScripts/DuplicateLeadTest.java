package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;

public class DuplicateLeadTest extends BaseClass {

	@Test
	public void duplicateLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(lead.getPageHeader(), "Leads");
		Thread.sleep(3000);
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");

		
		Map<String, String> map = excel.readFromExcel("Create and Duplicate Lead", "LeadsTestData");
		String leadName = map.get("Last Name") + jutil.generateRandom(100);
		String companyName = map.get("Company");
		createLead.setLastName(leadName);
		createLead.setCompanyName(companyName);
		createLead.clickSave();
		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(leadName));

		newLead.clickDuplicate();
		soft.assertTrue(leadDuplicate.getPageHeader().contains("Duplicating"));
		String newLastName = map.get("New Last Name") + jutil.generateRandom(100);
		leadDuplicate.setNewLastName(newLastName);
		leadDuplicate.clickSave();

		Thread.sleep(3000);
		soft.assertTrue(newLead.getPageHeader().contains(newLastName));
		newLead.clickLeadsLink();

		if (lead.getNewleadName().equals(newLastName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create and Duplicate Lead", "pass", IConstantPath.EXCEL_FILE_PATH,
					"LeadsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create and Duplicate Lead", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"LeadsTestData");
		}
		soft.assertTrue(lead.getNewleadName().contains(newLastName));
		soft.assertAll();
	}
}
