package pomPages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
 LoginPage login;
 HomePage home;
 OrganizationsPage org;
 ContactsPage contact;
 LeadsPage lead;
 CreateNewContactPage createContact;
 CreateNewOrgPage createOrg;
 CreateNewLeadPage createLead;
 CreateNewEventPage createEvent;
 DuplicatingLeadPage leadDuplicate;
 NewContactDetailPage newContact;
 NewEventDetailPage newEvent;
 NewLeadDetailPage newLead;
 NewOrgDetailsPage newOrg;
 WebDriver driver;
 
 public PageObjectManager(WebDriver driver)
 {
	 this.driver=driver;
 }
 public LoginPage getLoginPage() {
	 login=new LoginPage(driver);
	 return login;
 }
 public HomePage getHomePage() {
	 home=new HomePage(driver);
	 return home;
 }
 public OrganizationsPage getOrganizationsPage() {
	org =new OrganizationsPage(driver);
	 return org;
 }
 public ContactsPage getContactsPage() {
		contact =new ContactsPage(driver);
		 return contact;
	 }
 public LeadsPage getLeadsPage() {
		lead =new LeadsPage(driver);
		 return lead;
	 }
 public CreateNewContactPage getCreateNewContactPage() {
		createContact =new CreateNewContactPage(driver);
		 return createContact;
	 }
 public CreateNewOrgPage getCreateNewOrgPage() {
		createOrg = new CreateNewOrgPage(driver);
		return createOrg;
	}
 public CreateNewLeadPage getCreateNewLeadPage() {
		createLead = new CreateNewLeadPage(driver);
		return createLead;
	}
 public CreateNewEventPage getCreateNewEventPage() {
		createEvent = new CreateNewEventPage(driver);
		return createEvent;
	}
 public DuplicatingLeadPage getDuplicatingLeadPage() {
		leadDuplicate = new DuplicatingLeadPage(driver);
		return leadDuplicate;
	}
 public NewContactDetailPage getNewContactDetailPage() {
		newContact = new NewContactDetailPage(driver);
		return newContact;
	}
 public NewEventDetailPage getNewEventDetailPage() {
		newEvent = new NewEventDetailPage(driver);
		return newEvent;
	}
 public NewLeadDetailPage getNewLeadDetailPage() {
		newLead = new NewLeadDetailPage(driver);
		return newLead;
	}
 public NewOrgDetailsPage getNewOrgDetailsPage() {
		newOrg = new NewOrgDetailsPage(driver);
		return newOrg;
	}
 
 
 
 
 
 
 
}
	
	