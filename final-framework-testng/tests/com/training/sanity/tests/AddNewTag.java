package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddNewTagPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: TO Verify whether application allows admin to add new tag*/
public class AddNewTag 
{

	private WebDriver driver;
	private String baseUrl;
	private AddNewTagPOM AddNewTagPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String celltext2;
	private String Nametext1;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		//Before method executes the basic operations like opening Link & Logging in..
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AddNewTagPOM = new AddNewTagPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		AddNewTagPOM.clickSignInBtn(); 
		AddNewTagPOM.sendUserName("admin");
		AddNewTagPOM.sendPassword("admin@123");
		AddNewTagPOM.clickLoginBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void AddNewPostTest() throws InterruptedException 
	{
		AddNewTagPOM.clickPostsBtn(); 
		AddNewTagPOM.clcikTagsBtn();
		//Test Case: Tags page with Add New Tag module along with existing tag should get displayed
		screenShot.captureScreenShot("TagPageScreenshot"); 
		String PageTitle = driver.getTitle();
		//Test Case: Tags page should be displayed
		System.out.println("Tag page is being displayed, Page Title: " +PageTitle); 
		AddNewTagPOM.sendName("a");
		//String Nametext1 is being Asserted at the end.
		Nametext1 = ("a"); 
		//Test Case: Entered data in Name textbox should get displayed
		WebElement text = driver.findElement(By.id("tag-name"));
	    String EnteredName = text.getAttribute("value");
		System.out.println("Entered credentials in Name textbox is: " +EnteredName); 
		AddNewTagPOM.sendSlug("aa");
		//String SlugName is being Asserted at the end.
		String SlugName = ("aa"); 
		//Test Case: Entered data in Slug textbox should get displayed
		WebElement text1 = driver.findElement(By.id("tag-slug"));
	    String EnteredSlug = text1.getAttribute("value");
		System.out.println("Entered credentials in Slug textbox is: " +EnteredSlug); 
		AddNewTagPOM.sendDescription("aaa");
		//String DescriptionDetails is being Asserted at the end.
		String DescriptionDetails = ("aaa"); 
		//Test Case: Entered data in Description textbox should get displayed
		WebElement text2 = driver.findElement(By.id("tag-description"));
	    String EnteredDescription = text2.getAttribute("value");
		System.out.println("Entered credentials in Description textbox is: " +EnteredDescription); 
		AddNewTagPOM.clickAddNewTagBtn();
		AddNewTagPOM.clcikTagsBtn();
		Thread.sleep(3000);
		//To get the text of first cell of the table, asserted below.
		celltext2 = driver.findElement(By.xpath("//tbody/tr/td")).getText();
		System.out.println("Tag added: " +celltext2); //Test Case: Added tag in existing tags module/table should get displayed
		assertEquals(EnteredName, Nametext1);
		assertEquals(EnteredSlug, SlugName);
		assertEquals(EnteredDescription, DescriptionDetails);
		assertEquals(celltext2, Nametext1);
	}
}


