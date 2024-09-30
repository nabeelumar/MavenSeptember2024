package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponents.AbstractComponenets;

public class ProductsCatalogue extends AbstractComponenets {

	WebDriver driver;

	public ProductsCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	By productListBy = By.cssSelector(".mb-3");

	By addCart = By.cssSelector(".card-body button:last-of-type");

	By toastContainer = By.cssSelector(".toast-message");

	public List<WebElement> getProductList() {

		waitElement(productListBy);
		return productList;

	}

	public WebElement getProduct(String productName) {

		WebElement products = productList.stream().filter(
				product -> product.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return products;
	}

	public void addToCart(String productName) {

		WebElement products = getProduct(productName);

		products.findElement(addCart).click();

		waitElement(toastContainer);

	}

}
