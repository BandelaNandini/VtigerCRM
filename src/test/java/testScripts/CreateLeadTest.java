package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;

public class CreateLeadTest extends BaseClass {

	@Test
	public void createNewLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(lead.getPageHeader(), "Leads");
		Thread.sleep(3000);
		lead.clickPlusButton();
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Create lead", "LeadsTestData");
		String leadName = map.get("Last Name") + jutil.generateRandom(100);
		String companyName = map.get("Company");
		createLead.setLastName(leadName);
		createLead.setCompanyName(companyName);
		createLead.clickSave();
		soft.assertTrue(newLead.getPageHeader().contains(leadName));
		Thread.sleep(3000);
		newLead.clickLeadsLink();
		if (lead.getNewleadName().equals(leadName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create lead", "pass", IConstantPath.EXCEL_FILE_PATH, "LeadsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create lead", "Fail", IConstantPath.EXCEL_FILE_PATH, "LeadsTestData");
		}
		soft.assertTrue(lead.getNewleadName().contains(leadName));
		soft.assertAll();

		
	}
}