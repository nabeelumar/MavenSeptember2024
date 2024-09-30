package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#userEmail")
	WebElement username;

	@FindBy(css = "#userPassword")
	WebElement password;

	@FindBy(css = "#login")
	WebElement login;
	
	@FindBy(css=".toast-error")
	WebElement errorContainer;

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");

	}

	public ProductsCatalogue loginApplication(String uname, String pwd) {

		username.sendKeys(uname);
		password.sendKeys(pwd);
		login.click();
		ProductsCatalogue pc = new ProductsCatalogue(driver);
		return pc;
	}
	
	public String validateErrorMsg() {
		
		String errorMsg = errorContainer.getText();
		return errorMsg;
	}

}
