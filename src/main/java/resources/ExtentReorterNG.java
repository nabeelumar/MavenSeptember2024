package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReorterNG {
public 	static ExtentReports extent;
	
	public static  ExtentReports getReporter() {
		
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Ecommerce Autoamtion Test Results");
		reporter.config().setDocumentTitle("TestRestults");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nabeel");
		return extent;
		
	}

}
