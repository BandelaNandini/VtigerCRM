package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;
import pomPages.PageObjectManager;

public class CreateContactWithExistingOrgTest extends BaseClass {
PageObjectManager pageObjects;
	@Test
	public void createNewContactWithExistingOrgTest() throws InterruptedException {
		home.clickContacts();
		soft.assertEquals(pageObjects.getContactsPage().getPageHeader(), "Contacts");
		Thread.sleep(3000);
		pageObjects.getContactsPage().clickconPlusButton();
		soft.assertEquals(pageObjects.getCreateNewContactPage().getPageHeader(), "Creating New Contact");

		Map<String, String> map = excel.readFromExcel("Create Contact With Organization", "ContactsTestData");
		String contactName = map.get("Last Name") + jutil.generateRandom(100);
		pageObjects.getCreateNewContactPage().setLastName(contactName);
		pageObjects.getCreateNewContactPage().selectExistingOrg(web, map.get("Organization Name"));
		pageObjects.getCreateNewContactPage().clickSave();
		soft.assertTrue(pageObjects.getNewContactDetailPage().getPageHeader().contains(contactName));
		Thread.sleep(3000);
		pageObjects.getNewContactDetailPage().clickContactsLink();
		if (pageObjects.getContactsPage().getNewContactName().equals(contactName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create Contact With Organization", "pass", IConstantPath.EXCEL_FILE_PATH,
					"ContactsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create Contact With Organization", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"ContactsTestData");
		}
		soft.assertTrue(pageObjects.getContactsPage().getNewContactName().contains(contactName));
		soft.assertAll();
	}
}
