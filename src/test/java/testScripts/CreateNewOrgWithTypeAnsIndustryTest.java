package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;

public class CreateNewOrgWithTypeAnsIndustryTest extends BaseClass {

	@Test
	public void createNewOrgWithTypeAndIndustryTest() throws InterruptedException {
		home.clickOrganizations();
		soft.assertEquals(org.getPageHeader(), "Organizations");
		org.clickPlusButton();
		soft.assertEquals(createOrg.getPageHeader(), "Creating New Organization");


		Map<String, String> map = excel.readFromExcel("Create Organization With Industry And Type",
				"OrganizationsTestData");
		String orgName = map.get("Organization Name") + jutil.generateRandom(100);
		createOrg.setOrgName(orgName);
		createOrg.selectIndustry(web, map.get("Industry"));
		createOrg.selectType(web, map.get("Type"));
		createOrg.clickSave();
		soft.assertTrue(newOrg.getPageHeader().contains(orgName));
		Thread.sleep(3000);
		newOrg.clickOrgLink();
		if (org.getNewOrgName().equals(orgName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create Organization With Industry And Type", "pass", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create Organization With Industry And Type", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"OrganizationsTestData");
		}
		soft.assertEquals(org.getNewOrgName(), orgName);
		soft.assertAll();

	}

}
