package TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;


public class NegativeTest extends BaseTest{
	
	@Test
	public void invalidCredTest() {
	lp.loginApplication("shibil@gmail.com", "Abcd@123");
	String errorMessage = lp.validateErrorMsg();
	System.out.println(errorMessage);
	Assert.assertEquals(errorMessage, "Incorrect uname or password.");

}
}