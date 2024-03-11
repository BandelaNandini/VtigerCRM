package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;

public class CreateOrgTest extends BaseClass {

	@Test
	public void createNewOrgTest() throws InterruptedException {
		home.clickOrganizations();
		soft.assertEquals(org.getPageHeader(), "Organizations");
		org.clickPlusButton();
		soft.assertEquals(createOrg.getPageHeader(), "Creating New Organization");

		Map<String, String> map = excel.readFromExcel("Create Organization", "OrganizationsTestData");
		String orgName = map.get("Organization Name") + jutil.generateRandom(100);
		createOrg.setOrgName(orgName);
		createOrg.clickSave();
		Thread.sleep(3000);
		soft.assertTrue(newOrg.getPageHeader().contains(orgName));
		newOrg.clickOrgLink();
		if (org.getNewOrgName().equals(orgName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create Organization", "pass", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create Organization", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		}
		
		soft.assertEquals(org.getNewOrgName(), orgName);
		soft.assertAll();

	}

}
