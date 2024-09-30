package September.MavenSeptember2024;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Ecommerce {

	@Test
	public void placeOrder() {
		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.cssSelector("#userEmail")).sendKeys("nabeelans@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Abcd@123");

		driver.findElement(By.cssSelector("#login")).click();

		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wd.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

		WebElement products = productList.stream().filter(
				product -> product.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		products.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wd.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));

		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

		List<WebElement> orderedLists = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean isPresent = orderedLists.stream().anyMatch(orderedList -> orderedList.getText().contains(productName));
		Assert.assertTrue(isPresent);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();

		wd.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
		for (WebElement country : countries) {
			if (country.getText().equalsIgnoreCase("India")) {

				country.click();
				break;
			}
		}

		driver.findElement(By.cssSelector(".action__submit")).click();

		String message = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		System.out.println(message);
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

	}

}
