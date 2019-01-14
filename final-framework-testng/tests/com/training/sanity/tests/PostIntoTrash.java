package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.PostIntoTrashPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class PostIntoTrash 
{

	private WebDriver driver;
	private String baseUrl;
	private PostIntoTrashPOM PostIntoTrashPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private String celltext2;
	private String celltext1;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		PostIntoTrashPOM = new PostIntoTrashPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[8]/a")));
		PostIntoTrashPOM.clickSignInBtn(); 
		PostIntoTrashPOM.sendUserName("admin");
		PostIntoTrashPOM.sendPassword("admin@123");
		PostIntoTrashPOM.clickLoginBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void validTrashTest() throws InterruptedException 
	{
		PostIntoTrashPOM.clickPostsBtn(); 
		screenShot.captureScreenShot("PostsLinkDisplay"); //Test Case: All Posts added by the admin should get displayed
		WebElement poststable = driver.findElement(By.xpath("//*[@id=\"posts-filter\"]"));
		List < WebElement > rows_table = poststable.findElements(By.tagName("tr"));
		for (int row = 1; row < 2; row++) 
		{
			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			 for (int column = 0; column < 1; column++) 
			 {
				 	WebElement celltext = Columns_row.get(column);
				    celltext1 = Columns_row.get(column).getText();
				 	System.out.println("Value in the post List before Trash Button is clicked: " +celltext1);
				    Actions actions1 = new Actions(driver);
					actions1.moveToElement(celltext).build().perform();
					screenShot.captureScreenShot("MouseHoverDisplay"); //Test Case: Edit, Quick Edit, Trash, View, links below the posts should get displayed
			 }
		}
		PostIntoTrashPOM.clickTrashBtn(); //Test Case: Click on Trash link
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		String TrashMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		System.out.println("Message displayed after Trash button was clicked: " +TrashMsg); //Test case: 1 post moved to the Trash. Undo message should get displayed
		
		WebElement poststable1 = driver.findElement(By.xpath("//*[@id=\"posts-filter\"]"));
		List < WebElement > rows_table1 = poststable1.findElements(By.tagName("tr"));
		for (int row = 1; row < 2; row++) 
		{
			List < WebElement > Columns_row1 = rows_table1.get(row).findElements(By.tagName("td"));
			 for (int column = 0; column < 1; column++) 
			 {
				    celltext2 = Columns_row1.get(column).getText();
				 	System.out.println("Value in the post List after Trash Button was clicked: " +celltext2);
			 }
			 	//Test Case: Verification TC "post should get removed from the Post list".		 			 
			 if (celltext1.equals(celltext2))
			 {
					 System.out.println("Please look for any data issue first else raise a Defect");
					 assert (celltext1 == celltext2);
			 }
			 else
			 {
				 System.out.println("Post trashed successfully");
			 }

		}					
	
	}
}


