package generic_Utilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ListenersImplementation implements ITestListener{
	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	public static ExtentTest stest;
	BaseClass bc;
	
	@Override
	public void onStart(ITestContext context) {
		spark=new ExtentSparkReporter("./ExtentReports/reports.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Extent Reports");
		spark.config().setReportName("Vtiger CRM");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Author","Nandini");
		report.setSystemInfo("OS",System.getProperty("os.name"));
		report.setSystemInfo("OS Version",System.getProperty("os.version"));
		report.setSystemInfo("Java Version",System.getProperty("java.version"));
		
	}

	//@Override
//	public void onTestStart(ITestResult result) {
//		Capabilities cap = ((RemoteWebDriver) bc.Vtdriver).getCapabilities();
//		report.setSystemInfo("Browser", cap.getBrowserName() + " " + cap.getVersion());
//		test = report.createTest(result.getMethod().getMethodName());
//		stest = test;
//	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName()+"pass");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+"fail");
		test.fail(result.getThrowable());
		
		WebDriverUtility web=new WebDriverUtility();
	//	test.addScreenCaptureFromPath(web.captureScreenshot(bc.Vtdriver, bc.sjutil, result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+" skip");
		test.skip(result.getThrowable());
		
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
