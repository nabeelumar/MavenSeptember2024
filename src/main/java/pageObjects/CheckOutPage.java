package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponenets;

public class CheckOutPage extends AbstractComponenets {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	By result = By.cssSelector(".ta-results");

	By placeOrder = By.cssSelector(".action__submit");

	@FindBy(css = ".ta-results button")
	List<WebElement> countries;

	public void selectCountry() {
		Actions a = new Actions(driver);
		a.sendKeys(country, "Ind").build().perform();
		waitElement(result);
		for (WebElement country : countries) {
			if (country.getText().equalsIgnoreCase("India")) {

				country.click();
				break;
			}
		}

	}

	public ConfirmationPage placeProductOrder() {
		
		driver.findElement(placeOrder).click();
		ConfirmationPage cop = new ConfirmationPage(driver);
		return cop;
	}

	// driver.findElement(By.cssSelector(".action__submit")).click();
}
