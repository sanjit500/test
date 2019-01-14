package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.training.pom.AddNewPostPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: TO Verify whether application allows admin to add new post into list*/

public class AddNewPost 
{

	private WebDriver driver;
	private String baseUrl;
	private AddNewPostPOM AddNewPostPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String BodyTxt;
	private String titletext1;

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
		AddNewPostPOM = new AddNewPostPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		AddNewPostPOM.clickSignInBtn(); 
		AddNewPostPOM.sendUserName("admin");
		AddNewPostPOM.sendPassword("admin@123");
		AddNewPostPOM.clickLoginBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void AddNewPostTest() throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		AddNewPostPOM.clickPostsBtn(); 
		AddNewPostPOM.clcikaddnewBtn();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wpbody-content\"]/div[3]/h1")));
		//Test Case: Add New Post page should get displayed
		String PageTitle = driver.getTitle();
		System.out.println("Add New Post Page is being displayed, Page Title: " +PageTitle); 
		AddNewPostPOM.sendTitle("sanjit123");
		//String titletext1 is being Asserted at the end.
		titletext1 = ("sanjit123"); 
		AddNewPostPOM.sendContent("This is to test the addition of a new post");
		//String BodyTxt is being Asserted at the end.
		BodyTxt = ("This is to test the addition of a new post"); 
		Thread.sleep(3000);
		//To get the data entered for Title
	    WebElement text = driver.findElement(By.id("title"));
	    String EntrTitlTxt = text.getAttribute("value");
	    //Test Case: Entered data in Enter title here textbox should get displayed
		System.out.println("Entered credentials in 'Enter title here textbox' is: " +EntrTitlTxt); 
	    WebElement text1 = driver.findElement(By.xpath("//*[@id=\"content\"]"));
	    String EntredBodyText = text1.getAttribute("value");
	    //Test Case: Entered data in body textbox should get displayed
		System.out.println("Entered credentials in 'body textbox' is : " +EntredBodyText); 
		AddNewPostPOM.clickPublishBtn();
		//String DisplMsg is being Asserted at the end.
		String DisplMsg = ("Post published. View post"); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		//Test Case: Post published. View post message should get displayed
		System.out.println("Message appears after Post Published: " +PublishMsg); 
		assertEquals(EntrTitlTxt, titletext1);
		assertEquals(EntredBodyText, BodyTxt);
		assertEquals(PublishMsg, DisplMsg);
	}
}


