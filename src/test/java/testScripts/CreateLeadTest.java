package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;
import pomPages.PageObjectManager;

public class CreateLeadTest extends BaseClass {
PageObjectManager pageObjects;
	@Test
	public void createNewLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(pageObjects.getLeadsPage().getPageHeader(), "Leads");
		Thread.sleep(3000);
		pageObjects.getLeadsPage().clickPlusButton();
		soft.assertEquals(pageObjects.getCreateNewLeadPage().getPageHeader(), "Creating New Lead");

		Map<String, String> map = excel.readFromExcel("Create lead", "LeadsTestData");
		String leadName = map.get("Last Name") + jutil.generateRandom(100);
		String companyName = map.get("Company");
		pageObjects.getCreateNewLeadPage().setLastName(leadName);
		pageObjects.getCreateNewLeadPage().setCompanyName(companyName);
	    pageObjects.getCreateNewLeadPage().clickSave();
		soft.assertTrue(pageObjects.getNewLeadDetailPage().getPageHeader().contains(leadName));
		Thread.sleep(3000);
		pageObjects.getNewLeadDetailPage().clickLeadsLink();
		if (pageObjects.getLeadsPage().getNewleadName().equals(leadName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create lead", "pass", IConstantPath.EXCEL_FILE_PATH, "LeadsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create lead", "Fail", IConstantPath.EXCEL_FILE_PATH, "LeadsTestData");
		}
		soft.assertTrue(pageObjects.getLeadsPage().getNewleadName().contains(leadName));
		soft.assertAll();

		
	}
}