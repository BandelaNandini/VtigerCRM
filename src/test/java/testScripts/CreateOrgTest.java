package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;
import pomPages.PageObjectManager;

public class CreateOrgTest extends BaseClass {
	PageObjectManager pageObjects;

	@Test
	public void createNewOrgTest() throws InterruptedException {
		home.clickOrganizations();
		soft.assertEquals(pageObjects.getOrganizationsPage().getPageHeader(), "Organizations");
		pageObjects.getOrganizationsPage().clickPlusButton();
		soft.assertEquals(pageObjects.getCreateNewOrgPage().getPageHeader(), "Creating New Organization");

		Map<String, String> map = excel.readFromExcel("Create Organization", "OrganizationsTestData");
		String orgName = map.get("Organization Name") + jutil.generateRandom(100);
		pageObjects.getCreateNewOrgPage().setOrgName(orgName);
		pageObjects.getCreateNewOrgPage().clickSave();
		Thread.sleep(3000);
		soft.assertTrue(pageObjects.getNewOrgDetailsPage().getPageHeader().contains(orgName));
		pageObjects.getNewOrgDetailsPage().clickOrgLink();
		if (pageObjects.getOrganizationsPage().getNewOrgName().equals(orgName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create Organization", "pass", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create Organization", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		}
		
		soft.assertEquals(pageObjects.getOrganizationsPage().getNewOrgName(), orgName);
		soft.assertAll();

	}

}
