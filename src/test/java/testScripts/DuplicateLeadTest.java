package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;
import pomPages.PageObjectManager;


public class DuplicateLeadTest extends BaseClass {
	PageObjectManager pageObjects;

	@Test
	public void duplicateLeadTest() throws InterruptedException {
		home.clickLeads();
		soft.assertEquals(pageObjects.getLeadsPage().getPageHeader(), "Leads");
		Thread.sleep(3000);
		pageObjects.getLeadsPage().clickPlusButton();
		soft.assertEquals(pageObjects.getCreateNewLeadPage().getPageHeader(), "Creating New Lead");

		
		Map<String, String> map = excel.readFromExcel("Create and Duplicate Lead", "LeadsTestData");
		String leadName = map.get("Last Name") + jutil.generateRandom(100);
		String companyName = map.get("Company");
		pageObjects.getCreateNewLeadPage().setLastName(leadName);
		pageObjects.getCreateNewLeadPage().setCompanyName(companyName);
		pageObjects.getCreateNewLeadPage().clickSave();
		Thread.sleep(3000);
		soft.assertTrue(pageObjects.getNewLeadDetailPage().getPageHeader().contains(leadName));

		pageObjects.getNewLeadDetailPage().clickDuplicate();
		soft.assertTrue(pageObjects.getDuplicatingLeadPage().getPageHeader().contains("Duplicating"));
		String newLastName = map.get("New Last Name") + jutil.generateRandom(100);
		pageObjects.getDuplicatingLeadPage().setNewLastName(newLastName);
		pageObjects.getDuplicatingLeadPage().clickSave();

		Thread.sleep(3000);
		soft.assertTrue(pageObjects.getNewLeadDetailPage().getPageHeader().contains(newLastName));
		pageObjects.getNewLeadDetailPage().clickLeadsLink();

		if (pageObjects.getLeadsPage().getNewleadName().equals(newLastName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create and Duplicate Lead", "pass", IConstantPath.EXCEL_FILE_PATH,
					"LeadsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create and Duplicate Lead", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"LeadsTestData");
		}
		soft.assertTrue(pageObjects.getLeadsPage().getNewleadName().contains(newLastName));
		soft.assertAll();
	}
}
