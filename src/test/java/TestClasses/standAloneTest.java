package TestClasses;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrdersPage;
import pageObjects.ProductsCatalogue;

public class standAloneTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData")
	public void positiveTest(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductsCatalogue pc = lp.loginApplication(input.get("username"), input.get("password"));
		pc.addToCart(productName);
		CartPage cp = pc.goToCart();

		boolean productPresent = cp.productPresent(productName);
		Assert.assertTrue(productPresent);
		CheckOutPage co = cp.checkOutProduct();

		co.selectCountry();
		ConfirmationPage cop = co.placeProductOrder();

		Assert.assertEquals(cop.getMessage(), "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"positiveTest"})
	public void verifyOrderPresent() {
		ProductsCatalogue pc = lp.loginApplication("nabeelans@gmail.com", "Abcd@123");
		OrdersPage op = pc.goToOrders();
		boolean orderPresent = op.verifyOrder(productName);
		
		Assert.assertTrue(orderPresent);
	}
	
	@DataProvider(name ="getData")
	public Object[][] getDataTest() throws IOException {
		
		
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/data/purchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		/*HashMap<String, String> map1= new HashMap<String,String>();
		map1.put("username", "nabeelans@gmail.com");
		map1.put("password", "Abcd@123");
		
		HashMap<String, String> map2= new HashMap<String,String>();
		map2.put("username", "sonu@gmail.com");
		map2.put("password", "Abcd@123"); */
		
		//return new Object[][] {{map1},{map2}};
		
		//return new Object[][] {{"nabeelans@gmail.com","Abcd@123"},{"sonu@gmail.com","Abcd@123"}};
		
		
		
		
	}
	
	

}
