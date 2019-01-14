
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewPostPOM 
{
	private WebDriver driver;
	
	public AddNewPostPOM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//*[@id=\"responsive\"]/li[8]/a")
	private WebElement signin;
		
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@id=\"menu-posts\"]/a/div[2]")
	private WebElement posts;
	
	@FindBy(xpath="//*[@id=\"wpbody-content\"]/div[3]/a")
	private WebElement addnewBtn;
	
	@FindBy(id="title")
	private WebElement titleEnter;
	
	@FindBy(id="content")
	private WebElement contentEnter;
	
	@FindBy(id="publish")
	private WebElement publishBtn;
	
	public void clickSignInBtn() {
		this.signin.click(); 
	}
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickPostsBtn() {
		this.posts.click(); 
	}
	public void clcikaddnewBtn() {
		this.addnewBtn.click(); 
	}
	public void sendTitle(String titleEnter) {
		this.titleEnter.sendKeys(titleEnter); 
	}
	public void sendContent(String contentEnter) {
		this.contentEnter.sendKeys(contentEnter); 
	}
	public void clickPublishBtn() {
		this.publishBtn.click(); 
	}
	
}