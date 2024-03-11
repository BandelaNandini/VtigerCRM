package testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import generic_Utilities.BaseClass;
import generic_Utilities.IConstantPath;

public class CreateEventTest extends BaseClass{

	@Test
	public void createNewEventTest() throws InterruptedException {
		Map<String, String> map = excel.readFromExcel("Create New Event", "EventsTestData");
		home.selectFromQuickCreate(web, map.get("Quick Create"));
		soft.assertEquals(createEvent.getPageHeader(), "Create To Do");
		String subject = map.get("Subject") + jutil.generateRandom(100);
		createEvent.setSubject(subject);
		createEvent.setStartDate(map.get("Start Date"));
		createEvent.setEndDate(map.get("Due Date"));
		Thread.sleep(3000);
		createEvent.clickSave();
		soft.assertTrue(newEvent.getPageHeader().contains(subject));
		Thread.sleep(3000);
		
		if (newEvent.getPageHeader().contains(subject)) {
			System.out.println("test pass");
			excel.updatedTestStatus("Create New Event", "pass", IConstantPath.EXCEL_FILE_PATH, "EventsTestData");
		} else {
			System.out.println("test fail");
			excel.updatedTestStatus("Create New Event", "Fail", IConstantPath.EXCEL_FILE_PATH, "EventsTestData");
		}
		soft.assertAll();
	}
}
