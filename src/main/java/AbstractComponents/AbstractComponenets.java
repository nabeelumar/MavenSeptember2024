package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrdersPage;

public class AbstractComponenets {

	WebDriver driver;

	public AbstractComponenets(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*=cart]")
	WebElement cart;
	
	@FindBy(css = "[routerlink*=myorders]")
	WebElement orders;

	public void waitElement(By ListBy) {
		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wd.until(ExpectedConditions.visibilityOfElementLocated(ListBy));

	}

	public CartPage goToCart() {
		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrdersPage goToOrders() {
		
		orders.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}

}
