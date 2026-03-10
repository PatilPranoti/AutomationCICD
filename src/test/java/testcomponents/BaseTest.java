package testcomponents;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import selframe.hq.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initialiseDriver() throws IOException
	{		
		
		//create properties object
		Properties prop=new Properties();
		//converting it into stream 
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
		prop.load(fis);//prop.load require stream file
		
		String browserName=System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");//	String browserName=prop.getProperty("browser");
		
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");

			}
			driver =new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//fullscreen
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException{
		driver = initialiseDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
		
	}
	/*@AfterMethod
	public void tearDown()
	{
		driver.close();
	}*/

	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);
	//string to hashmap jackson databing helps to convert string to hashmap
	
		
		ObjectMapper mapper=new ObjectMapper();
		
	List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
	{
		
	});
	return data;
	
	}
	
	public String getScreenshot(String tastCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+tastCaseName+".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+tastCaseName+".png";
		
	}
	
	
	
	
}
