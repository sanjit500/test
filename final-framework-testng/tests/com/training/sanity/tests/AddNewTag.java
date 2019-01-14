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
		screenShot.captureScreenShot("TagPageScreenshot"); //Test Case: Tags page with Add New Tag module along with existing tag should get displayed
		String PageTitle = driver.getTitle();
		System.out.println("Tag page is being displayed, Page Title: " +PageTitle); //Test Case: Tags page should be displayed
		AddNewTagPOM.sendName("a");
		Nametext1 = ("a"); //String Nametext1 is being Asserted at the end.
		WebElement text = driver.findElement(By.id("tag-name"));
	    String EnteredName = text.getAttribute("value");
		System.out.println("Entered credentials in Name textbox is: " +EnteredName); //Test Case: Entered credentials in Name textbox should get displayed
		AddNewTagPOM.sendSlug("aa");
		String SlugName = ("aa"); //String SlugName is being Asserted at the end.
		WebElement text1 = driver.findElement(By.id("tag-slug"));
	    String EnteredSlug = text1.getAttribute("value");
		System.out.println("Entered credentials in Slug textbox is: " +EnteredSlug); //Test Case: Entered credentials in Slug textbox should get displayed
		AddNewTagPOM.sendDescription("aaa");
		String DescriptionDetails = ("aaa"); //String DescriptionDetails is being Asserted at the end.
		WebElement text2 = driver.findElement(By.id("tag-description"));
	    String EnteredDescription = text2.getAttribute("value");
		System.out.println("Entered credentials in Description textbox is: " +EnteredDescription); //Test Case: Entered credentials in Description textbox should get displayed
		AddNewTagPOM.clickAddNewTagBtn();
		AddNewTagPOM.clcikTagsBtn();
		Thread.sleep(3000);
		//Below code is to find first cell of the table.
		WebElement Tagtable = driver.findElement(By.xpath("//*[@id=\"posts-filter\"]"));
		List < WebElement > rows_table = Tagtable.findElements(By.tagName("tr"));
		for (int row = 1; row < 2; row++) 
		{
		List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			 for (int column = 0; column < 1; column++) 
			 {
		celltext2 = Columns_row.get(column).getText(); //To get the text of first cell of the table, asserted below.
		System.out.println("Tag added: " +celltext2); //Test Case: Added tag in existing tags module should get displayed
			 }

		}
		assertEquals(EnteredName, Nametext1);
		assertEquals(EnteredSlug, SlugName);
		assertEquals(EnteredDescription, DescriptionDetails);
		assertEquals(celltext2, Nametext1);
	}
}


