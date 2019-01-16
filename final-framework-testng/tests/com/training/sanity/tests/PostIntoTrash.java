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

/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows  Admin to add the post into trash*/
//

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
		
		//Before method executes the basic operations like opening Link & Logging in..
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
		//All test cases are executed under this method.
		PostIntoTrashPOM.clickPostsBtn();
		//Test Case: All Posts added by the admin should get displayed
		screenShot.captureScreenShot("PostsLinkDisplay"); 
		//WebElement celltext has been assigned for mouse hover operation.
		WebElement celltext = driver.findElement(By.xpath("//tbody/tr/td")); 
		//String celltext1 assigned to print the value and assert at at the end of program.
		celltext1 = driver.findElement(By.xpath("//tbody/tr/td")).getText(); 
	 	System.out.println("Value in the post List before Trash Button is clicked: " +celltext1);
	    Actions actions1 = new Actions(driver);
		actions1.moveToElement(celltext).build().perform();
		//Test Case: Edit, Quick Edit, Trash, View, links below the posts should get displayed
		screenShot.captureScreenShot("MouseHoverDisplay"); 

		//Test Case: Click on Trash link
		PostIntoTrashPOM.clickTrashBtn(); 
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		String TrashMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		//Test case: 1 post moved to the Trash. Undo message should get displayed
		System.out.println("Message displayed after Trash button was clicked: " +TrashMsg); 
		//String celltext2 assigned to print the value and assert at at the end of program.
		celltext2 = driver.findElement(By.xpath("//tbody/tr/td")).getText();
	 	System.out.println("Value in the post List after Trash Button was clicked: " +celltext2);
	 	
	 	//Below If-Else statement verifies if the data has been removed or not from table after the Trash button was clicked.
	 	
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


