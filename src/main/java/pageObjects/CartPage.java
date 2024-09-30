package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	
	WebDriver driver;

	public CartPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> orderedLists;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	
	public boolean productPresent(String productName) {
		
		boolean present = orderedLists.stream().anyMatch(orderedList -> orderedList.getText().contains(productName));
		return present;
		
	}
	
	
	public CheckOutPage checkOutProduct() {
		checkOut.click();
		CheckOutPage co = new CheckOutPage(driver);
		return co;
		
	}
	
}
