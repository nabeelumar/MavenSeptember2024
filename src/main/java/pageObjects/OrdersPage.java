package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".table td:nth-child(3)")
	List<WebElement> orderList;
	
	public boolean verifyOrder(String productName) {
		
		boolean orderPresent = orderList.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
		return orderPresent;
	}

	


}
