package TestComponents;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logge;
import resources.ExtentReorterNG;

public class Listeners extends BaseTest  implements ITestListener {

	private static  org.apache.logging.log4j.Logger log = LogManager.getLogger(Listeners.class.getName());
	ExtentReports extent = ExtentReorterNG.getReporter();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		log.info("Test "+result.getMethod().getMethodName()+"  has started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "Test is passed");
		log.info("Test "+result.getMethod().getMethodName()+"  has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		log.error("Test "+result.getMethod().getMethodName()+"  has failed");
		extentTest.get().fail(result.getThrowable());
		
		System.out.println("TEST 11111111111111111");
		
		String testCaseName = result.getMethod().getMethodName();
		System.out.println("TEST 2222222222222");
		WebDriver driver = null;
		
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
 
 
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
	e1.printStackTrace();
		}
 
		
		
			
		
		
		
		try {
			String destinationPath = getScreenshot(testCaseName,driver);
			 
			extentTest.get().addScreenCaptureFromPath(destinationPath, testCaseName);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		
		extent.flush();
	}

}
