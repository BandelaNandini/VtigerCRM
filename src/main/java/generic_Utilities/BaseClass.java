package generic_Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pomPages.ContactsPage;
import pomPages.CreateNewContactPage;
import pomPages.CreateNewEventPage;
import pomPages.CreateNewLeadPage;
import pomPages.CreateNewOrgPage;
import pomPages.DuplicatingLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewContactDetailPage;
import pomPages.NewEventDetailPage;
import pomPages.NewLeadDetailPage;
import pomPages.NewOrgDetailsPage;
import pomPages.OrganizationsPage;

public class BaseClass {
//	@BeforeSuite
//	@BeforeTest
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected JavaUtility jutil;
	protected WebDriverUtility web;

	protected WebDriver driver;
	protected SoftAssert soft;

	protected LoginPage login;
	protected HomePage home;
	protected OrganizationsPage org;
	protected ContactsPage contact;
	protected LeadsPage lead;
	protected CreateNewContactPage createContact;
	protected CreateNewOrgPage createOrg;
	protected CreateNewLeadPage createLead;
	protected CreateNewEventPage createEvent;
	protected DuplicatingLeadPage leadDuplicate;
	protected NewContactDetailPage newContact;
	protected NewEventDetailPage newEvent;
	protected NewLeadDetailPage newLead;
	protected NewOrgDetailsPage newOrg;

	@BeforeClass
	public void classSetup() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		jutil = new JavaUtility();
		web = new WebDriverUtility();

		property.propertiesInit(IConstantPath.PROPERTIES_FILE_PATH);
		driver = web.launchBrowser(property.readFromProperties("browser"));
		web.waitTillElementFound(Long.parseLong(property.readFromProperties("timeouts")));
	}

	@BeforeMethod
	public void methodSetup() {

		login = new LoginPage(driver);
		home = new HomePage(driver);
		contact = new ContactsPage(driver);
		org = new OrganizationsPage(driver);
		createContact = new CreateNewContactPage(driver);
		createLead = new CreateNewLeadPage(driver);
		createOrg = new CreateNewOrgPage(driver);
		createEvent = new CreateNewEventPage(driver);
		leadDuplicate = new DuplicatingLeadPage(driver);
		newContact = new NewContactDetailPage(driver);
		newOrg = new NewOrgDetailsPage(driver);
		newLead = new NewLeadDetailPage(driver);
		newEvent = new NewEventDetailPage(driver);
		lead = new LeadsPage(driver);
		excel.excelInit(IConstantPath.EXCEL_FILE_PATH);
		soft = new SoftAssert();

		web.navigateToApp(property.readFromProperties("url"));
		Assert.assertEquals(login.getPageHeader(), "vtiger");
		login.loginToVtiger(property.readFromProperties("username"), property.readFromProperties("password"));
		Assert.assertTrue(home.getPageHeader().contains("Home"));
	}

	@AfterMethod

	public void methodTearDown() {
		home.signOutOfApp(web);
		excel.closeExcel();
	}

	@AfterClass
	public void classTeardown() {
		web.quitAllWindows();
	}
//	@AfterTest
//	@AfterSuite

}
