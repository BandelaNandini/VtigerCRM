package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;

public class CreateContactWithExistingOrgTest extends BaseClass {

	@Test
	public void createNewContactWithExistingOrgTest() throws InterruptedException {
		home.clickContacts();
		soft.assertEquals(contact.getPageHeader(), "Contacts");
		Thread.sleep(3000);
		contact.clickconPlusButton();
		soft.assertEquals(createContact.getPageHeader(), "Creating New Contact");

		Map<String, String> map = excel.readFromExcel("Create Contact With Organization", "ContactsTestData");
		String contactName = map.get("Last Name") + jutil.generateRandom(100);
		createContact.setLastName(contactName);
		createContact.selectExistingOrg(web, map.get("Organization Name"));
		createContact.clickSave();
		soft.assertTrue(newContact.getPageHeader().contains(contactName));
		Thread.sleep(3000);
		newContact.clickContactsLink();
		if (contact.getNewContactName().equals(contactName)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create Contact With Organization", "pass", IConstantPath.EXCEL_FILE_PATH,
					"ContactsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create Contact With Organization", "Fail", IConstantPath.EXCEL_FILE_PATH,
					"ContactsTestData");
		}
		soft.assertTrue(contact.getNewContactName().contains(contactName));
		soft.assertAll();
	}
}
