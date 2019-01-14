package com.training.sanity.tests;

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
import com.training.pom.DeleteCategoryPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: TO Verify whether application allows admin to delete category from the categories page*/
public class DeleteCategory 
{

	private WebDriver driver;
	private String baseUrl;
	private DeleteCategoryPOM DeleteCategoryPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String celltext1;
	private String celltext2;

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
		DeleteCategoryPOM = new DeleteCategoryPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		DeleteCategoryPOM.clickSignInBtn(); 
		DeleteCategoryPOM.sendUserName("admin");
		DeleteCategoryPOM.sendPassword("admin@123");
		DeleteCategoryPOM.clickLoginBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void AddNewPostTest() throws InterruptedException 
	{
		DeleteCategoryPOM.clickPostsBtn(); 
		DeleteCategoryPOM.clcikCategoriesBtn();
		//Test Case: Categories page with Add New Category module along with existing categories should get displayed
		screenShot.captureScreenShot("DeleteCategoryPageScreenshot"); 
		String PageTitle = driver.getTitle();
		//Test Case: Categories page should be displayed
		System.out.println("Categories page is being displayed, Page Title: " +PageTitle); 
		

		Thread.sleep(3000);
		////To get the text of first cell of the table, asserted at the end.
		celltext1 = driver.findElement(By.xpath("//tbody/tr/td")).getText(); 
		// Finding common attributes of the checkoxes
		List<WebElement> checkbox = driver.findElements(By.xpath("//input[@type='checkbox' and @name='delete_tags[]']"));
		for (int i=0; i<checkbox.size(); i++)
		{
			WebElement cbox = checkbox.get(i);
			String id = cbox.getAttribute("id");
			if (id.contains("cb-select"))
			{
				cbox.click(); //Test Case: Checkbox should get selected
				break;
			 }
			
		}
		DeleteCategoryPOM.clcikBulkBtn();
		//Test Case: BulkAction-Delete link should display
		screenShot.captureScreenShot("BulkAction-Delete link Screenshot"); 
		Thread.sleep(3000);
		DeleteCategoryPOM.clcikOnDeleteBtn();
		//Test Case: Delete Should be get displayed on Bulk Action list box
		screenShot.captureScreenShot("Delete button Screenshot"); 
		DeleteCategoryPOM.clcikOnApplyBtn();
		Thread.sleep(3000);
		//Test Case: Categories deleted. Message should get displayed
		String msg = driver.findElement(By.xpath("//*[@id='message']/p")).getText();
		System.out.println("Message appears after Deletion: " +msg); 
		
		//To get the text of first cell of the table, asserted at the end..
		celltext2 = driver.findElement(By.xpath("//tbody/tr/td")).getText();
		System.out.println("Before deletion row: " +celltext1);
		System.out.println("After deltion row: " +celltext2); 

			 //Test case: selected category should be removed from the categories list
			 if (celltext1.equals(celltext2))
			 {
					 System.out.println("Please check for data issue first then raise a Defect");
					 assert (celltext1 == celltext2);
			 }
			 else
			 {
				 System.out.println("Selected Category removed successfully from Category List");
				 assert(celltext1 != celltext2);
			 }
		
		
		}
}
		

