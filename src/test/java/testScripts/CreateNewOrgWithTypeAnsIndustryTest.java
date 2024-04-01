package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;
import pomPages.PageObjectManager;

public class CreateNewOrgWithTypeAnsIndustryTest extends BaseClass {
PageObjectManager pageObjects;
	@Test
	public void createNewOrgWithTypeAndIndustryTest() throws InterruptedException {
		home.clickOrganizations();
		soft.assertEquals(pageObjects.getOrganizationsPage().getPageHeader(), "Organizations");
		pageObjects.getOrganizationsPage().clickPlusButton();
		soft.assertEquals(pageObjects.getCreateNewOrgPage().getPageHeader(), "Creating New Organization");


		Map<String, String> map = excel.readFromExcel("Create Organization With Industry And Type",
				"OrganizationsTestData");
		String orgName = map.get("Organization Name") + jutil.generateRandom(100);
		pageObjects.getCreateNewOrgPage().setOrgName(orgName);
		pageObjects.getCreateNewOrgPage().selectIndustry(web, map.get("Industry"));
		pageObjects.getCreateNewOrgPage().selectType(web, map.get("Type"));
		pageObjects.getCreateNewOrgPage().clickSave();
		soft.assertTrue(pageObjects.getNewOrgDetailsPage().getPageHeader().contains(orgName));
		Thread.sleep(3000);
		pageObjects.getNewOrgDetailsPage().clickOrgLink();
		if (pageObjects.getOrganizationsPage().getNewOrgName().equals(orgName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create Organization With Industry And Type", "pass", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create Organization With Industry And Type", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		}
		soft.assertEquals(pageObjects.getOrganizationsPage().getNewOrgName(), orgName);
		soft.assertAll();

	}

}
