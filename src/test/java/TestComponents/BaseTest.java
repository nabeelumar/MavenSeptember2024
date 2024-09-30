package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp ;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/global.properties");
		prop.load(fis);
		String browser = System.getProperty("browser")!=null?System.getProperty("browser"):
				prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			ChromeOptions options  = new ChromeOptions();
			
			if(browser.contains("headless")) {
				
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			Dimension d = new Dimension(1440,900);
			driver.manage().window().setSize(d);
			
		}else if(browser.contains("edge")){
			
			driver = new EdgeDriver();
			
		}else if(browser.contains("safari")){
			
			driver = new SafariDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.manage().window().maximize();
		return driver;
		
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launcURL() throws IOException {
		
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
		
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		
		//convert Json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//Convert string to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destinationPath = System.getProperty("user.dir")+"//reports//screenshots//"+testCaseName+".png";
	//	System.out.println("Screenshot path s :"+destinationPath);
	//	String destinationPath = System.getProperty("user.dir")+"//reports//screenshots//"+testCaseName+".png";
		FileUtils.copyFile(src, new File(destinationPath));
		return destinationPath;
		
		
	
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
	}

}
