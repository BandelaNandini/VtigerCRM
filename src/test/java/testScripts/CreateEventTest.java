package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;
import pomPages.PageObjectManager;

public class CreateEventTest extends BaseClass{
PageObjectManager pageObjects;
	@Test
	public void createNewEventTest() throws InterruptedException {
		Map<String, String> map = excel.readFromExcel("Create New Event", "EventsTestData");
		home.selectFromQuickCreate(web, map.get("Quick Create"));
		soft.assertEquals(pageObjects.getCreateNewEventPage().getPageHeader(), "Create To Do");
		String subject = map.get("Subject") + jutil.generateRandom(100);
		pageObjects.getCreateNewEventPage().setSubject(subject);
		pageObjects.getCreateNewEventPage().setStartDate(map.get("Start Date"));
		pageObjects.getCreateNewEventPage().setEndDate(map.get("Due Date"));
		Thread.sleep(3000);
		pageObjects.getCreateNewEventPage().clickSave();
		soft.assertTrue(pageObjects.getNewEventDetailPage().getPageHeader().contains(subject));
		Thread.sleep(3000);
		
		if (pageObjects.getNewEventDetailPage().getPageHeader().contains(subject)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create New Event", "pass", IConstantPath.EXCEL_FILE_PATH, "EventsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create New Event", "Fail", IConstantPath.EXCEL_FILE_PATH, "EventsTestData");
		}
		soft.assertAll();
	}
}
